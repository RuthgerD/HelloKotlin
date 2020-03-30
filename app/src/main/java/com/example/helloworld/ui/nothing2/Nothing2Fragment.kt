package com.example.helloworld.ui.nothing2

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.helloworld.R

class Nothing2Fragment : Fragment() {

    private lateinit var nothing2ViewModel: Nothing2ViewModel

    private var m_bluetoothAdapter: BluetoothAdapter? = null
    private lateinit var m_pairedDevices: Set<BluetoothDevice>
    private val REQUEST_ENABLE_BLUETOOTH = 1

    val hanl = Handler()
    val bt_devices = ArrayList<BluetoothDevice>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        nothing2ViewModel = ViewModelProvider(this).get(Nothing2ViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_nothing2, container, false)
        val textView: TextView = root.findViewById(R.id.text_nothing2)
        nothing2ViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        m_bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (m_bluetoothAdapter == null || ContextCompat.checkSelfPermission(
                requireActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            println("no bluetooth")
        } else {
            println("we got bluetooth")
        }

        if (!m_bluetoothAdapter!!.isEnabled) {
            val enableBluetoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBluetoothIntent, REQUEST_ENABLE_BLUETOOTH)
        }

        root.findViewById<Button>(R.id.select_device_refresh).setOnClickListener { discoverDevices() }
        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)

        
        requireActivity().registerReceiver(receiver, filter)
        val filter2 = IntentFilter(BluetoothDevice.ACTION_BOND_STATE_CHANGED)
        requireActivity().registerReceiver(receiver, filter2)
        return root
    }

    fun discoverDevices() {
        bt_devices.clear()
        if (m_bluetoothAdapter!!.startDiscovery())
            println("Starting discovery")
        else
            println("Discovery failed")

        val runnable = Runnable {
            m_bluetoothAdapter?.cancelDiscovery()
            println("Found devices:")
            bt_devices.forEach { println("* ${it.name}") }

            if(bt_devices.last().createBond())
                println("Bonding to " + bt_devices.last().name)
            //m_bluetoothAdapter.listenUsingRfcommWithServiceRecord("", )
        }
        hanl.postDelayed(runnable, 3000)
    }

    private fun pairedDeviceList() {
        m_pairedDevices = m_bluetoothAdapter!!.bondedDevices
        val list: ArrayList<BluetoothDevice> = ArrayList()

        if (m_pairedDevices.isNotEmpty()) {
            for (device: BluetoothDevice in m_pairedDevices) {
                list.add(device)
                println("device $device")
            }
        } else {
            println("no paired bluetooth devices found")
        }
    }

    private val receiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                BluetoothDevice.ACTION_FOUND -> {
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    device?.let {
                        val deviceName = it.name
                        val deviceHardwareAddress = it.address // MAC address
                        bt_devices.add(device)
                    }
                }
                BluetoothDevice.ACTION_BOND_STATE_CHANGED -> {
                    val device: BluetoothDevice? =
                        intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE)
                    val bond_info: Int? =
                        intent.getIntExtra(BluetoothDevice.EXTRA_PREVIOUS_BOND_STATE, -1)
                    println("$device.name $bond_info")
                }
            }
        }
    }

    override fun onDestroy() {
        requireActivity().unregisterReceiver(receiver)
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ENABLE_BLUETOOTH) {
            if (resultCode == Activity.RESULT_OK) {
                if (m_bluetoothAdapter!!.isEnabled) {
                    println("Bluetooth has been enabled")
                } else {
                    println("Bluetooth has been disabled")
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                println("Bluetooth enabling has been canceled")
            }
        }
    }



}
