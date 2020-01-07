package com.intelia.loansdk


import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.intelia.loansdk.vm.MainVM
import com.intelia.loansdk.vm.VMFactory
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var mainVM: MainVM
    val RC_PHONE = 1
    lateinit var progressDialog: ProgressDialog

    private fun startLoading(message: String? = null) {
        toggleNoMessageView(false)
        toggleResponseView(false)
        if (message != null) progressDialog.setMessage(message)
        progressDialog.show()
    }

    private fun endLoading() {
        progressDialog.dismiss()
    }

    private fun toggleResponseView(show: Boolean) {
        eligibility_response_view.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun toggleNoMessageView(show: Boolean) {
        no_messages_text.visibility = if (show) View.VISIBLE else View.INVISIBLE
    }

    private fun smsQuery() {
        startLoading()
        mainVM.querySms(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainVM = ViewModelProviders.of(this, VMFactory).get(MainVM::class.java)

        mainVM.smsDataPoint.observe(this, Observer {
            Log.e("size",it.size.toString())
            endLoading()
        })

        setupProgressDialog()
        EasyPermissions.requestPermissions(
            this,
            getString(R.string.app_name),
            RC_PHONE,
            android.Manifest.permission.READ_SMS,
            android.Manifest.permission.RECEIVE_SMS
        )
    }

    private fun setupProgressDialog() {
        progressDialog = ProgressDialog(this@MainActivity)
        progressDialog.setMessage("Calculating Eligibility...")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {

    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        smsQuery()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        smsQuery()
        return super.onOptionsItemSelected(item)
    }
}
