#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
cd "$ROOT_DIR"

if [[ $# -lt 1 ]]; then
  echo "Usage: ./run-exercise.sh <exercise-number>"
  echo "Example: ./run-exercise.sh 7"
  exit 1
fi

EX="$1"

run_android_gradle() {
  local dir="$1"
  cd "$dir"
  if [[ -z "${ANDROID_HOME:-}" && -z "${ANDROID_SDK_ROOT:-}" && ! -f local.properties ]]; then
    echo "Android SDK path not detected."
    echo "Open this folder once in Android Studio, let it sync, then re-run."
    exit 1
  fi
  chmod +x ./gradlew
  ./gradlew assembleDebug
  echo "Build completed: $dir"
}

case "$EX" in
  1)
    run_android_gradle "exercise-01-android-button-style"
    ;;
  2)
    run_android_gradle "exercise-02-android-calculator"
    ;;
  3)
    run_android_gradle "exercise-03-android-notification"
    ;;
  4)
    run_android_gradle "exercise-04-android-library-management"
    ;;
  5)
    cd exercise-05-cordova-login
    npm install
    npx cordova platform add android || true
    npx cordova run android
    ;;
  6)
    cd exercise-06-cordova-location
    npm install
    npx cordova platform add android || true
    npx cordova run android
    ;;
  7)
    cd exercise-07-ionic-task-manager
    npm install
    npm run build
    npx ionic serve
    ;;
  8)
    cd exercise-08-react-native-bmi
    npm install
    npx expo start
    ;;
  9)
    cd exercise-09-flutter-expense-manager
    flutter create .
    flutter pub get
    flutter run
    ;;
  10)
    run_android_gradle "exercise-10-android-unit-converter"
    ;;
  11)
    run_android_gradle "exercise-11-android-lost-found"
    ;;
  *)
    echo "Invalid exercise number: $EX"
    echo "Valid values: 1 to 11"
    exit 1
    ;;
esac
