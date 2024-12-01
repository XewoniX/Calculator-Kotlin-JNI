//
// Created by xewon on 29.11.2024.
//
#include <jni.h>
#include <stdexcept>

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_example_calc_NativeCalculator_add(JNIEnv *env, jobject, jdouble a, jdouble b) {
    return a + b;
}
extern "C"
JNIEXPORT jdouble JNICALL
Java_com_example_calc_NativeCalculator_subtract(JNIEnv *env, jobject, jdouble a, jdouble b) {
    return a - b;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_example_calc_NativeCalculator_multiply(JNIEnv *env, jobject, jdouble a, jdouble b) {
    return a * b;
}

extern "C"
JNIEXPORT jdouble JNICALL
Java_com_example_calc_NativeCalculator_divide(JNIEnv *env, jobject, jdouble a, jdouble b) {
    if (b == 0) {
        throw std::invalid_argument("Dzielenie przez 0");
    }
    return a / b;
}




