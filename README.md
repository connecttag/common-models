# common-models

Pure Kotlin primitives shared by many libraries.

## KMP status

This module is a Kotlin Multiplatform library. `src/commonMain/kotlin` is the source of truth for Android and JVM consumers.

## Owns

- `Result`
- `DomainError`
- `UiState`
- `CollectionResult`
- Mapping contracts that are generic across domains.

## Boundaries

- Do not add leaf-library models here.
- Device, favorite, network, telephony, country, notification, and UI-specific models must stay in their owning modules.
- Keep this module Android-free and serialization-friendly.

## Usage

Use this module only for generic operation state, generic domain errors, collection availability, and mapping contracts.
