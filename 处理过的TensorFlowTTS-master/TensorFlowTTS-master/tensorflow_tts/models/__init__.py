from tensorflow_tts.models.fastspeech import TFFastSpeech
from tensorflow_tts.models.fastspeech2 import TFFastSpeech2
from tensorflow_tts.models.discriminator import Discriminator
from tensorflow_tts.models.melgan import (
    TFMelGANDiscriminator,
    TFMelGANGenerator,
    TFMelGANMultiScaleDiscriminator,
)
from tensorflow_tts.models.mb_melgan import TFPQMF
from tensorflow_tts.models.mb_melgan import TFMBMelGANGenerator
from tensorflow_tts.models.tacotron2 import TFTacotron2
from tensorflow_tts.models.parallel_wavegan import TFParallelWaveGANGenerator
from tensorflow_tts.models.parallel_wavegan import TFParallelWaveGANDiscriminator
from tensorflow_tts.models.fastspeech_voice_cloning import TFFastSpeech as TFFastSpeechClone
from tensorflow_tts.models.fastspeech2_voice_cloning import TFFastSpeech2 as TFFastSpeech2Clone


