package com.example.weatherhub.ui.contacts

import android.app.AlertDialog
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.weatherhub.R
import com.example.weatherhub.databinding.FragmentPhoneContactsBinding
import com.example.weatherhub.utils.REQUEST_PERMISSION_CONTACTS_CODE


class PhoneContactsFragment : Fragment() {

    private var _binding: FragmentPhoneContactsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhoneContactsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
    }

    private fun checkPermission() {
//       есть ли разрешение
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                android.Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getContacts()
        } else if (shouldShowRequestPermissionRationale(android.Manifest.permission.READ_CONTACTS)) {
            // важно написать убедительную просьбу
            explain()
        } else {
            mRequestPermission()
        }
    }

    private fun mRequestPermission() {
        requestPermissions(
            arrayOf(android.Manifest.permission.READ_CONTACTS),
            REQUEST_PERMISSION_CONTACTS_CODE
        )
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSION_CONTACTS_CODE) {
            if (grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED
            ) {
                getContacts()
            } else {
                explain()
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    private fun getContacts() {
        val contentResolver: ContentResolver = requireContext().contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME + " ASC"
        )

        cursor?.let {
            for (i in 0 until it.count) {
                if (cursor.moveToPosition(i)) {
                    val columnIndexCursor =
                        cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    val name: String = cursor.getString(columnIndexCursor)
                    binding.containerForContacts.addView(TextView(requireContext()).apply {
                        textSize = 30f
                        text = name
                    })
                }
            }
        }
        cursor?.close()
    }

    private fun explain() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.dialog_rationale_title))
            .setMessage(getString(R.string.dialog_rationale_message))
            .setPositiveButton(getString(R.string.dialog_rationale_give_access))
            { _, _ ->
                mRequestPermission()
            }
            .setNegativeButton(getString(R.string.dialog_rationale_decline)) {
                    dialog, _ -> dialog.dismiss() }
            .create()
            .show()

    }

    companion object {
        fun newInstance() =
            PhoneContactsFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}