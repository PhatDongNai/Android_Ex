// Generated by view binder compiler. Do not edit!
package com.nguyentanphat.pre_test.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.nguyentanphat.pre_test.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityAddBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button btnCancel;

  @NonNull
  public final Button btnCapture;

  @NonNull
  public final Button btnSave;

  @NonNull
  public final EditText edtCategory;

  @NonNull
  public final EditText edtDes;

  @NonNull
  public final EditText edtName;

  @NonNull
  public final EditText edtPrice;

  @NonNull
  public final ImageView imvPhoto;

  private ActivityAddBinding(@NonNull LinearLayout rootView, @NonNull Button btnCancel,
      @NonNull Button btnCapture, @NonNull Button btnSave, @NonNull EditText edtCategory,
      @NonNull EditText edtDes, @NonNull EditText edtName, @NonNull EditText edtPrice,
      @NonNull ImageView imvPhoto) {
    this.rootView = rootView;
    this.btnCancel = btnCancel;
    this.btnCapture = btnCapture;
    this.btnSave = btnSave;
    this.edtCategory = edtCategory;
    this.edtDes = edtDes;
    this.edtName = edtName;
    this.edtPrice = edtPrice;
    this.imvPhoto = imvPhoto;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityAddBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityAddBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_add, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityAddBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnCancel;
      Button btnCancel = ViewBindings.findChildViewById(rootView, id);
      if (btnCancel == null) {
        break missingId;
      }

      id = R.id.btnCapture;
      Button btnCapture = ViewBindings.findChildViewById(rootView, id);
      if (btnCapture == null) {
        break missingId;
      }

      id = R.id.btnSave;
      Button btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.edtCategory;
      EditText edtCategory = ViewBindings.findChildViewById(rootView, id);
      if (edtCategory == null) {
        break missingId;
      }

      id = R.id.edtDes;
      EditText edtDes = ViewBindings.findChildViewById(rootView, id);
      if (edtDes == null) {
        break missingId;
      }

      id = R.id.edtName;
      EditText edtName = ViewBindings.findChildViewById(rootView, id);
      if (edtName == null) {
        break missingId;
      }

      id = R.id.edtPrice;
      EditText edtPrice = ViewBindings.findChildViewById(rootView, id);
      if (edtPrice == null) {
        break missingId;
      }

      id = R.id.imvPhoto;
      ImageView imvPhoto = ViewBindings.findChildViewById(rootView, id);
      if (imvPhoto == null) {
        break missingId;
      }

      return new ActivityAddBinding((LinearLayout) rootView, btnCancel, btnCapture, btnSave,
          edtCategory, edtDes, edtName, edtPrice, imvPhoto);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
