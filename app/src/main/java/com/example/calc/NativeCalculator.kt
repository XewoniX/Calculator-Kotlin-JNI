package com.example.calc

class NativeCalculator {
    companion object {
        init {
            System.loadLibrary("calc")
        }
    }
    external fun add(a: Double, b: Double): Double
    external fun subtract(a: Double, b: Double): Double
    external fun multiply(a: Double, b: Double): Double
    external fun divide(a: Double, b: Double): Double
}