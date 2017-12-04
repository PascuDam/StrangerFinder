// Generated code from Butter Knife. Do not modify!
package com.example.strangerfinder.strangerfinder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WaitingActivity_ViewBinding implements Unbinder {
  private WaitingActivity target;

  @UiThread
  public WaitingActivity_ViewBinding(WaitingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WaitingActivity_ViewBinding(WaitingActivity target, View source) {
    this.target = target;

    target.btSearchAqain = Utils.findRequiredViewAsType(source, R.id.bt_search_again, "field 'btSearchAqain'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WaitingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btSearchAqain = null;
  }
}
