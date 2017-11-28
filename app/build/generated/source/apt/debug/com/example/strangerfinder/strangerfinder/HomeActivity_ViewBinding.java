// Generated code from Butter Knife. Do not modify!
package com.example.strangerfinder.strangerfinder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeActivity_ViewBinding implements Unbinder {
  private HomeActivity target;

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public HomeActivity_ViewBinding(HomeActivity target, View source) {
    this.target = target;

    target.btStart = Utils.findRequiredViewAsType(source, R.id.bt_start, "field 'btStart'", Button.class);
    target.btStartToChat = Utils.findRequiredViewAsType(source, R.id.bt_start_to_chat, "field 'btStartToChat'", Button.class);
    target.etUsername = Utils.findRequiredViewAsType(source, R.id.et_username, "field 'etUsername'", EditText.class);
    target.rgSex = Utils.findRequiredViewAsType(source, R.id.rg_your_sex, "field 'rgSex'", RadioGroup.class);
    target.rgPreference = Utils.findRequiredViewAsType(source, R.id.rg_looking_for, "field 'rgPreference'", RadioGroup.class);
    target.rbSexFem = Utils.findRequiredViewAsType(source, R.id.rb_female, "field 'rbSexFem'", RadioButton.class);
    target.rbSexMal = Utils.findRequiredViewAsType(source, R.id.rb_male, "field 'rbSexMal'", RadioButton.class);
    target.rbPreferenceFem = Utils.findRequiredViewAsType(source, R.id.rb_lf_female, "field 'rbPreferenceFem'", RadioButton.class);
    target.rbPreferenceMal = Utils.findRequiredViewAsType(source, R.id.rb_lf_male, "field 'rbPreferenceMal'", RadioButton.class);
    target.rbPreferenceBoth = Utils.findRequiredViewAsType(source, R.id.rb_lf_both, "field 'rbPreferenceBoth'", RadioButton.class);
    target.lyMenu = Utils.findRequiredViewAsType(source, R.id.ly_menu, "field 'lyMenu'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btStart = null;
    target.btStartToChat = null;
    target.etUsername = null;
    target.rgSex = null;
    target.rgPreference = null;
    target.rbSexFem = null;
    target.rbSexMal = null;
    target.rbPreferenceFem = null;
    target.rbPreferenceMal = null;
    target.rbPreferenceBoth = null;
    target.lyMenu = null;
  }
}
