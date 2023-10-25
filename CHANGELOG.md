# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](http://keepachangelog.com/en/1.0.0/) and this project adheres to [Semantic Versioning](http://semver.org/spec/v2.0.0.html).
Prior to version 6.0.0, this project used MCVERSION-MAJORMOD.MAJORAPI.MINOR.PATCH.

## [6.2.1+1.20.1] - 2023.10.24
### Changed
- Updated to SpectreLib 0.13.14
- [Fabric] Requires Fabric Loader >=0.14.23

## [6.2.0+1.20.1] - 2023.06.18
### Added
- Added in-game configuration GUI
### Changed
- Updated to Minecraft 1.20.1
- Updated comments on configuration options

## [6.1.0+1.19.4] - 2023.05.18
### Changed
- Updated to Minecraft 1.19.4

## [6.0.0+1.19.2] - 2022.08.27
### Changed
- Merged Forge and Fabric versions of the project together using the [MultiLoader template](https://github.com/jaredlll08/MultiLoader-Template)
- Configuration system is now provided by SpectreLib
- Configuration file is now located in the root folder's `config` folder
- `additionalCreatures` configuration option can now accept entity type tags, tag entries should be namespaced and
prefaced by "#"
- Changed to Semantic Versioning
- Updated to Minecraft 1.19.2
- [Forge] Updated to Forge 43+
- [Fabric] Updated to Fabric API 0.60.0+

## [1.18.1-5.3.0.0] - 2022.04.14
### Changed
- Refactored to use mixins
### Fixed
- Fixed ConcurrentModificationException crash [#6](https://github.com/TheIllusiveC4/BeaconsForAll/issues/6)

## [1.18.1-5.2.0.0] - 2022.01.15
### Changed
- Updated to Minecraft 1.18.1
- Updated to Forge 38.0+

## [1.17.1-5.1.0.2] - 2021.07.25
### Changed
- Updated to Minecraft 1.17.1

## [1.16.5-5.1.0.1] - 2021.01.21
### Changed
- Updated to Minecraft 1.16.4
### Fixed
- Fixed villagers not being considered passive mobs [#2](https://github.com/TheIllusiveC4/BeaconsForAll/issues/2)

## [1.16.4-5.1.0.0] - 2020.11.16
### Changed
- Updated to Minecraft 1.16.4

## [1.16.3-5.0.0.1] - 2020.09.29
### Changed
- Updated to Minecraft 1.16.3

## [1.16.2-5.0.0.0] - 2020.08.17
### Changed
- Updated to Minecraft 1.16.2

## [1.16.1-4.0.1.0] - 2020.08.01
### Changed
- Tamed category now includes horses and saddled entities

## [1.16.1-4.0.0.0] - 2020.07.03
### Changed
- Updated to Minecraft 1.16.1

## [1.15.2-3.0.0.0] - 2020.02.14
### Changed
- Updated to Minecraft 1.15.2

## [1.14.4-2.0.0.0] - 2019.09.14
### Changed
- Updated to Forge 28.1.0

## [1.14.4-2.0.0.0-beta2] - 2019.09.08
### Changed
- Updated to Minecraft 1.14.4

## [1.13.2-2.0.0.0-beta1] - 2019.04.30
### Changed
- Updated to Minecraft 1.13.2

## [1.12.2-1.0.1.0] - 2018.12.09
### Added
- Added configuration option for specifying additional mobs outside the creature type config option

## [1.12.2-1.0.0.0] - 2018.12.08
Initial release
