package com.morley.tom.weatherquiz.base

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.annotation.ColorInt
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.morley.tom.weatherquiz.utils.ColourUtil

abstract class BaseActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            arguments(savedInstanceState)
        }
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViews()
    }

    /**
     * Argument intercept method. To handle argument imports to the project
     *   Override to import arguments
     */
    open fun arguments(bundle: Bundle) {
    }

    /**
     * Initialise the views
     */
    open fun initViews() {
    }

    override fun onResume() {
        super.onResume()
    }

    //region Abstract methods

    /**
     * Abstract method to get the layout ID.
     *   Will be used in the onCreate to handle layout inflation
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    //region Status bar

    fun setStatusBar(@ColorInt col: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ColourUtil.darken(col)
        }
    }

    //endregion

    //region Permissions

    /**
     * Request a given permission
     * - Callbacks will be fired below under permissionDenied, permissionGranted, or permissionShowRational
     */
    fun requestPermission(permission: String) {
        if (ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED) {
            permissionGranted(permission)
        }
        else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                permissionShowRational(permission)
            }
            else {
                ActivityCompat.requestPermissions(this, arrayOf(permission), PERMISSION_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSION_REQUEST_CODE && permissions.isNotEmpty()) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionGranted(permissions[0])
            }
            else {
                permissionDenied(permissions[0])
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    /**
     * Method fired when a permission is denied
     */
    open fun permissionDenied(perm: String) {

    }

    /**
     * Method fired when a permission is granted
     */
    open fun permissionGranted(perm: String) {

    }

    /**
     * Method fired when a permission should show rational
     */
    open fun permissionShowRational(perm: String) {

    }

    //endregion

    //region Fragments

    fun loadFragment(frag: Fragment, @IdRes layoutRes: Int, tag: String?) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        if (tag != null) {
            transaction.replace(layoutRes, frag, tag)
        }
        else {
            transaction.replace(layoutRes, frag)
        }
        transaction.commit()
    }

    fun loadFragment(frag: Fragment, @IdRes layoutRes: Int) {
        loadFragment(frag, layoutRes, null)
    }

    fun loadFragment(frag: android.app.Fragment, @IdRes layoutRes: Int) {
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(layoutRes, frag)
        transaction.commit()
    }


    //endregion

    companion object {
        @JvmField
        val PERMISSION_REQUEST_CODE: Int = 1001
    }
}