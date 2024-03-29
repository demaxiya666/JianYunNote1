package com.henu.jianyunnote.controller.index;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.henu.jianyunnote.dao.INoteBookDao_Bmob;
import com.henu.jianyunnote.dao.impl.INoteBookDaoImpl_Bmob;
import com.henu.jianyunnote.model.Bmob.NoteBook_Bmob;
import com.henu.jianyunnote.model.Bmob.Note_Bmob;
import com.henu.jianyunnote.model.Bmob.Users_Bmob;
import com.henu.jianyunnote.R;
import com.henu.jianyunnote.util.AtyUtil;
import com.henu.jianyunnote.util.MD5Util;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegisterController extends AppCompatActivity {
    private Button Return;
    private INoteBookDao_Bmob noteBookDao_bmob = new INoteBookDaoImpl_Bmob();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_register );
        AtyUtil.getInstance().addActivity(this);
        Bmob.initialize(this, "bc95d28fa2c059530870d4dbb550b38f");
        Button register_btn = findViewById(R.id.register);
        Button return_btn = findViewById(R.id.Return);
        final TextView Email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final EditText repassword = findViewById(R.id.repassword);
        final EditText safepassword = findViewById(R.id.safepassword);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(LoginController.isEmail(Email.getText().toString())==false)
                {
                    String info = "请输入正确的邮箱";
                    Toast.makeText(RegisterController.this, info, Toast.LENGTH_LONG).show();
                }
                else if((password.getText().toString()).equals(repassword.getText().toString())&&(password.getText().length()!=0)&&(safepassword.getText().length()!=0))
                {
                    String email_s= Email.getText().toString();
                    String password_s= MD5Util.Encode(password.getText().toString());
                    String safepassword_s=safepassword.getText().toString();
                    final Users_Bmob users =new Users_Bmob();
                    users.setEmail(email_s);
                    users.setPassword(password_s);
                    users.setSafePassword(safepassword_s);
                    users.save(new SaveListener<String>() {
                        @Override
                        public void done(String s, BmobException e) {
                            if(e==null)
                            {
//                                NoteBook_Bmob noteBook_bmob = noteBookDao_bmob.insert2NoteBook("未命名筆記本", users.getObjectId());
//                                noteDao_bmob.insert2Note("test_title","test",noteBook_bmob.getNotebook_id(),users.getObjectId());
                                final NoteBook_Bmob noteBook_bmob = new NoteBook_Bmob();
                                noteBook_bmob.setIsDelete(0);
                                noteBook_bmob.setNoteBookName("未命名筆記本");
                                noteBook_bmob.setUserId(users.getObjectId());
                                noteBook_bmob.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if(e==null)
                                        {
                                            Note_Bmob note_bmob = new Note_Bmob();
                                            note_bmob.setTitle("test");
                                            note_bmob.setContent("222");
                                            note_bmob.setNoteBookId(noteBook_bmob.getObjectId());
                                            note_bmob.setUserId(users.getObjectId());
                                            note_bmob.save(new SaveListener<String>() {
                                                @Override
                                                public void done(String s, BmobException e) {

                                                }
                                            });
                                        }
                                    }
                                });
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterController.this);
                                builder.setMessage("注册成功，请返回登录");
                                builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        finish();
                                    }
                                });
                                builder.show();
                            }
                            else
                            {
                                String info = "注册失败"+e.getErrorCode();
                                Toast.makeText(RegisterController.this, info, Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                }
                else if(password.getText().length()==0)
                {
                    String info = "请输入密码";
                    Toast.makeText(RegisterController.this, info, Toast.LENGTH_LONG).show();
                }
                else if(repassword.getText().length()==0)
                {
                    String info = "请确认密码";
                    Toast.makeText(RegisterController.this, info, Toast.LENGTH_LONG).show();
                }
                else if(safepassword.getText().length()==0)
                {
                    String info = "请输入密保密码";
                    Toast.makeText(RegisterController.this, info, Toast.LENGTH_LONG).show();
                }
                else if(password.getText().toString()!=repassword.getText().toString())
                {
                    String info = "输入的两次密码不一致，请重试";
                    Toast.makeText(RegisterController.this, info, Toast.LENGTH_LONG).show();
                }
            }


        });
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterController.this, LoginController.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
