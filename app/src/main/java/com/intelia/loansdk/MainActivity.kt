package com.intelia.loansdk


import android.app.ProgressDialog
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.intelia.loansdk.vm.MainVM
import com.intelia.loansdk.vm.VMFactory
import com.intelia.sdk.loanEligibility.LoanEligibility
import kotlinx.android.synthetic.main.activity_main.*
import pub.devrel.easypermissions.EasyPermissions
import xyz.belvi.myapplication.AlertRecyclerAdapter


class MainActivity : AppCompatActivity(), EasyPermissions.PermissionCallbacks {

    private lateinit var mainVM: MainVM
    val RC_PHONE = 1
    lateinit var progressDialog: ProgressDialog

    private  val smsAdapter = AlertRecyclerAdapter(mutableListOf())
    private fun startLoading(message: String? = null) {
        if (message != null) progressDialog.setMessage(message)
        progressDialog.show()
    }

    private fun endLoading() {
        progressDialog.dismiss()
    }



    private fun smsQuery() {
        startLoading()
        mainVM.querySms()
        mainVM.calculateEligibility()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sms_list.layoutManager = (LinearLayoutManager(this,
            RecyclerView.VERTICAL,false))
        sms_list.adapter = smsAdapter







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
        LoanEligibility.init(this)
        mainVM = ViewModelProviders.of(this, VMFactory).get(MainVM::class.java)

        mainVM.smsDataPoint.observe(this, Observer { res ->
            smsAdapter.update(res)
            endLoading()
        })
        mainVM.eligibility.observe(this, Observer { res ->
            res?.let {
                textView.text = Gson().toJson(res)
            }?:run{
                textView.text = "error fetching eligibility"
            }

            endLoading()
        })
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
