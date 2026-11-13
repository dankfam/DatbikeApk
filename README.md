# Quantum S3 Simulator — Android APK

This is a native Android WebView wrapper around the supplied standalone Quantum S3 simulator, plus a simple home-screen battery dashboard widget. It does not connect to the motorcycle; widget values are simulator estimates.

## Build the personal APK with GitHub Actions

1. Create a **new public GitHub repository** (for example `quantum-s3-android`).
2. Upload the contents of this folder to the repository root (the `.github` folder must be included).
3. Open the repository's **Actions** tab. Allow workflows if GitHub asks.
4. Run **Build Android APK** using **Run workflow**, or push a commit. The workflow installs Android SDK 35 and Gradle 8.9, then builds the APK.
5. When the workflow finishes, open its run and download the `Quantum-S3-Simulator-APK` artifact.
6. Extract the artifact ZIP on your Android phone, tap `app-debug.apk`, and allow installation from that source if Android prompts.

## Add the widget

After installing, long-press an empty area of the home screen → Widgets → Quantum S3 Simulator → Battery dashboard. Open the app once and let it calculate/update values. The widget shows the last simulator snapshot sent while the app is open; it is not live motorcycle telemetry.

## Notes

- The app uses the original HTML simulator bundled locally; no internet connection is required for the simulator itself.
- LocalStorage is retained by the Android WebView between launches.
- This workflow produces a **debug-signed personal APK**, suitable for sideloading. It is not a Play Store release.
- Package ID: `com.quantum.s3simulator`.
