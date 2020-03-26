package com.jetbrains.handson.mpp.mobile

import platform.UIKit.UIDevice

actual fun platformFunc(): String {
  return "An " + UIDevice.currentDevice.systemName() +
         " " +
         UIDevice.currentDevice.systemVersion + " function"
}
