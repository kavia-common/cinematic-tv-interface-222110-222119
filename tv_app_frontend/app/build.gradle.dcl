androidApplication {
    namespace = "org.example.app"

    // Note: Declarative DSL allows only limited edits; dependencies block updated to include Compose + TV libs
    dependencies {
        // Jetpack Compose UI libs (explicit versions to comply with project rules)
        implementation("androidx.compose.runtime:runtime:1.7.3")
        implementation("androidx.compose.ui:ui:1.7.3")
        implementation("androidx.compose.foundation:foundation:1.7.3")
        implementation("androidx.compose.ui:ui-tooling-preview:1.7.3")
        implementation("androidx.compose.ui:ui-tooling:1.7.3")
        implementation("androidx.activity:activity-compose:1.9.3")
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
        implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.6")
        implementation("androidx.compose.material3:material3:1.3.0")

        // AndroidX TV Compose libraries
        implementation("androidx.tv:tv-foundation:1.0.0-alpha10")
        implementation("androidx.tv:tv-material:1.0.0-alpha10")

        // Navigation for Compose
        implementation("androidx.navigation:navigation-compose:2.8.3")

        // Image loading with Coil for Compose
        implementation("io.coil-kt:coil-compose:2.6.0")

        // Keep project utilities if needed for potential string ops
        implementation(project(":utilities"))
    }
}
