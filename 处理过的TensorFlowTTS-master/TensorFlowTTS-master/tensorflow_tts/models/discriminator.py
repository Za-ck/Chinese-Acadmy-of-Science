import numpy as np
import tensorflow as tf

def get_initializer(initializer_seed=42):
    """Creates a `tf.initializers.glorot_normal` with the given seed.
    Args:
        initializer_seed: int, initializer seed.
    Returns:
        GlorotNormal initializer with seed = `initializer_seed`.
    """
    return tf.keras.initializers.GlorotNormal(seed=initializer_seed)

class TFReflectionPad1d(tf.keras.layers.Layer):
    """Tensorflow ReflectionPad1d module."""

    def __init__(self, padding_size, padding_type="REFLECT", **kwargs):
        """Initialize TFReflectionPad1d module.

        Args:
            padding_size (int)
            padding_type (str) ("CONSTANT", "REFLECT", or "SYMMETRIC". Default is "REFLECT")
        """
        super().__init__(**kwargs)
        self.padding_size = padding_size
        self.padding_type = padding_type

    def call(self, x):
        """Calculate forward propagation.
        Args:
            x (Tensor): Input tensor (B, T, C).
        Returns:
            Tensor: Padded tensor (B, T + 2 * padding_size, C).
        """
        return tf.pad(
            x,
            [[0, 0], [self.padding_size, self.padding_size], [0, 0]],
            self.padding_type,
        )

class SpectralNormalization(tf.keras.layers.Layer):
    """
        谱归一化层的一个包装，用来加上SN。
    """

    def __init__(self, layer, **kwargs):
        super().__init__(**kwargs)
        self.layer = layer

    def spectral_norm(self, w, r=5):
        w_shape = tf.keras.backend.int_shape(w)
        in_dim = np.prod(w_shape[:-1]).astype(int)
        out_dim = w_shape[-1]
        w = tf.keras.backend.reshape(w, (in_dim, out_dim))
        u = tf.keras.backend.ones((1, in_dim))
        for i in range(r):
            v = tf.keras.backend.l2_normalize(tf.keras.backend.dot(u, w))
            u = tf.keras.backend.l2_normalize(tf.keras.backend.dot(v, tf.keras.backend.transpose(w)))
        return tf.keras.backend.sum(tf.keras.backend.dot(tf.keras.backend.dot(u, w), tf.keras.backend.transpose(v)))

    def spectral_normalization(self, w):
        return w / self.spectral_norm(w)

    def __call__(self, inputs):
        with tf.keras.backend.name_scope(self.layer.name):
            if not self.layer.built:
                input_shape = tf.keras.backend.int_shape(inputs)
                self.layer.build(input_shape)
                self.layer.built = True
                if self.layer._initial_weights is not None:
                    self.layer.set_weights(self.layer._initial_weights)
        if not hasattr(self.layer, 'spectral_normalization'):
            if hasattr(self.layer, 'kernel'):
                self.layer.kernel = self.spectral_normalization(self.layer.kernel)
            if hasattr(self.layer, 'gamma'):
                self.layer.gamma = self.spectral_normalization(self.layer.gamma)
            self.layer.spectral_normalization = True
        return self.layer(inputs)


class Discriminator(tf.keras.Model):
    def __init__(self, config, **kwargs):
        """Init layers for fastspeech."""
        super().__init__(config, **kwargs)
        layers = []
        # first
        layers += [
            # (batch_size,length,80) -> (batch_size,length + 2 * kernel_sizes,80)
            TFReflectionPad1d(
                config.kernel_sizes, padding_type=config.padding_type
            ),
            # nn.Conv1d(1, 16, kernel_size=15)
            # SpectralNormalization 传入参数为layer
            SpectralNormalization(
                # (batch_size,length + 2 * 3,80) -> (batch_size, length + 2 * 3 - kernel_size ,filters)
                tf.keras.layers.Conv1D(
                    filters=config.filters[0],
                    kernel_size=int(config.conv_kernel_sizes[0]),
                    use_bias=config.use_bias,
                    kernel_initializer=get_initializer(config.initializer_seed),
                )
            ),
            tf.keras.layers.LeakyReLU(0.2)
        ]
        # second
        layers += [
            SpectralNormalization(
                tf.keras.layers.Conv1D(
                    filters=config.filters[1],
                    kernel_size=int(config.conv_kernel_sizes[1]),
                    use_bias=config.use_bias,
                    kernel_initializer=get_initializer(config.initializer_seed),
                )
            ),
            tf.keras.layers.LeakyReLU(0.2)
        ]
        # third
        layers += [
            SpectralNormalization(
                tf.keras.layers.Conv1D(
                    filters=config.filters[2],
                    kernel_size=int(config.conv_kernel_sizes[1]),
                    use_bias=config.use_bias,
                    kernel_initializer=get_initializer(config.initializer_seed),
                )
            ),
            tf.keras.layers.LeakyReLU(0.2)
        ]
        # forth
        # layers += [
        #     SpectralNormalization(
        #         tf.keras.layers.Conv1D(
        #             filters=config.filters[3],
        #             kernel_size=int(config.conv_kernel_sizes[1]),
        #             use_bias=config.use_bias,
        #             kernel_initializer=get_initializer(config.initializer_seed),
        #         )
        #     ),
        #     tf.keras.layers.LeakyReLU(0.2)
        # ]
        # fifth
        layers += [
            SpectralNormalization(
                tf.keras.layers.Conv1D(
                    filters=config.filters[4],
                    kernel_size=int(config.conv_kernel_sizes[1]),
                    use_bias=config.use_bias,
                    kernel_initializer=get_initializer(config.initializer_seed),
                )
            ),
            tf.keras.layers.LeakyReLU(0.2)
        ]
        # sixth
        layers += [
            SpectralNormalization(
                tf.keras.layers.Conv1D(
                    filters=config.filters[5],
                    kernel_size=int(config.conv_kernel_sizes[2]),
                    use_bias=config.use_bias,
                    kernel_initializer=get_initializer(config.initializer_seed),
                )
            ),
            tf.keras.layers.LeakyReLU(0.2)
        ]
        # the last
        layers += [
            SpectralNormalization(
                tf.keras.layers.Conv1D(
                    filters=config.filters[6],
                    kernel_size=int(config.conv_kernel_sizes[3]),
                    use_bias=config.use_bias,
                    kernel_initializer=get_initializer(config.initializer_seed),
                )
            )
        ]
        # TODO ERROR
        # tensorflow.python.framework.errors_impl.InvalidArgumentError:
        # Computed output size would be negative: -28
        # [input_size: 12, effective_filter_size: 41, stride: 1] [Op:Conv2D]
        self.discriminator = tf.keras.models.Sequential(layers)

    def call(self,mels,training=False,**kwargs,):
        """Call logic."""
        return self.discriminator(mels)

