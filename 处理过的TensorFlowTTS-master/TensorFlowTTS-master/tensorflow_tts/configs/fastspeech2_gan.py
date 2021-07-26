

class DiscriminatorConfig(object):
    """Initialize FastSpeech2_GAN Discriminator Config."""
    # todo modify this
    def __init__(
        self,
        kernel_sizes=3,
        filters=[16,64,256,1024,1024,1024,1],
        conv_kernel_sizes=[15,41,5,3],
        use_bias=True,
        padding_type="REFLECT",
        initializer_seed=42,
        **kwargs
    ):
        """Init parameters for MelGAN Discriminator model."""
        self.kernel_sizes = kernel_sizes
        self.filters = filters
        self.conv_kernel_sizes = conv_kernel_sizes
        self.use_bias = use_bias
        self.padding_type = padding_type
        self.initializer_seed = initializer_seed
