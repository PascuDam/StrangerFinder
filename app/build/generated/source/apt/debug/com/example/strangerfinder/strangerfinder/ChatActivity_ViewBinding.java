// Generated code from Butter Knife. Do not modify!
package com.example.strangerfinder.strangerfinder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ChatActivity_ViewBinding implements Unbinder {
  private ChatActivity target;

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChatActivity_ViewBinding(ChatActivity target, View source) {
    this.target = target;

    target.btnEnviar = Utils.findRequiredViewAsType(source, R.id.btnEnviar, "field 'btnEnviar'", Button.class);
    target.txtTexto = Utils.findRequiredViewAsType(source, R.id.txtTexto, "field 'txtTexto'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btnEnviar = null;
    target.txtTexto = null;
  }
}
