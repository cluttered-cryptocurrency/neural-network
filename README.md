# Neural Network

[![Build Status](https://travis-ci.org/cluttered-cryptocurrency/neural-network.svg?branch=master)](https://travis-ci.org/cluttered-cryptocurrency/neural-network)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/c6188b0aaf50430a92cebf29189f6f84)](https://www.codacy.com/app/cluttered-code/neural-network?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=cluttered-cryptocurrency/neural-network&amp;utm_campaign=Badge_Grade)
[![codecov](https://codecov.io/gh/cluttered-cryptocurrency/neural-network/branch/master/graph/badge.svg)](https://codecov.io/gh/cluttered-cryptocurrency/neural-network)

[![Java: 8](https://img.shields.io/badge/java-8-blue.svg)](http://docs.oracle.com/javase/8/docs/api/)
[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/cluttered-cryptocurrency/neural-network/master/LICENSE)

[![JitPack](https://jitpack.io/v/cluttered-cryptocurrency/neural-network.svg)](https://jitpack.io/#cluttered-cryptocurrency/neural-network)

## XOR Example

|   P   |   Q   | P&oplus;Q |
| :---: | :---: | :-------: |
|   0   |   0   |     0     |
|   1   |   0   |     1     |
|   0   |   1   |     1     |
|   1   |   1   |     0     |

![XOR Diagram](https://raw.githubusercontent.com/cluttered-cryptocurrency/neural-network/master/xor-neural-network.png)

```json
{
  "inputSize": 2,
  "layers": [
    {
      "neurons": [
        {
          "bias": -90,
          "activation": "SIGMOID",
          "weights": [60, 60]
        },
        {
          "bias": -40,
          "activation": "SIGMOID",
          "weights": [80, 80]
        }
      ]
    },
    {
      "neurons": [
        {
          "bias": -30,
          "activation": "SIGMOID",
          "weights": [-60, 60]
        }
      ]
    }
  ]
}
```