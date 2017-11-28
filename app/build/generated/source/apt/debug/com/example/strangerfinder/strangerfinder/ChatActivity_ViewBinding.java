// Generated code from Butter Knife. Do not modify!
package com.example.strangerfinder.strangerfinder;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
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

    target.lvMessages = Utils.findRequiredViewAsType(source, R.id.lw_messages, "field 'lvMessages'", ListView.class);
    target.btSend = Utils.findRequiredViewAsType(source, R.id.bt_send, "field 'btSend'", FloatingActionButton.class);
    target.et_message = Utils.findRequiredViewAsType(source, R.id.et_message, "field 'et_message'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChatActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lvMessages = null;
    target.btSend = null;
    target.et_message = null;
  }
}
