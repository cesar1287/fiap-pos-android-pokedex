package br.com.heiderlopes.pokemonwstemplatev2.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import me.dm7.barcodescanner.zxing.ZXingScannerView

abstract class BaseScanActivity : AppCompatActivity() {

    private val cameraResult = 101
    abstract val baseScannerView: ZXingScannerView?

    abstract fun onPermissionDenied()
    abstract fun onPermissionGranted()

    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            onPermissionGranted()
        } else {
            if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                onPermissionDenied()
            }
            requestPermissions(arrayOf(Manifest.permission.CAMERA), cameraResult)
        }
    }

    public override fun onPause() {
        super.onPause()
        baseScannerView?.stopCamera()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == cameraResult) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                onPermissionGranted()
            } else {
                onPermissionDenied()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }
}
