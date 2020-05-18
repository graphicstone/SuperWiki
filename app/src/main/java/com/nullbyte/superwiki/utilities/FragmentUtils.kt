package com.nullbyte.superwiki.utilities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.addFragmentWithoutBackStack(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransactionWithoutBackStack { add(frameId, fragment) }
}

fun AppCompatActivity.addFragmentWithData(fragment: Fragment, frameId: Int, data: Bundle) {
    fragment.arguments = data
    supportFragmentManager.inTransaction { add(frameId, fragment) }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int) {
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.replaceFragmentWithData(fragment: Fragment, frameId: Int, data: Bundle) {
    fragment.arguments = data
    supportFragmentManager.inTransaction { replace(frameId, fragment) }
}

fun AppCompatActivity.popBackStack() {
    supportFragmentManager.popBackStack()
}

fun AppCompatActivity.removeFragment(fragment: Fragment) {
    supportFragmentManager.inTransaction { remove(fragment) }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().addToBackStack(null).commit()
}

inline fun FragmentManager.inTransactionWithoutBackStack(func: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().func().commit()
}