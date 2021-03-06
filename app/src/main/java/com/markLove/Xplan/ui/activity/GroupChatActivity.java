package com.markLove.Xplan.ui.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cjt2325.cameralibrary.JCameraView;
import com.cjt2325.cameralibrary.util.FileUtil;
import com.dmcbig.mediapicker.PickerConfig;
import com.dmcbig.mediapicker.entity.Media;
import com.markLove.Xplan.R;
import com.markLove.Xplan.base.App;
import com.markLove.Xplan.base.ui.BaseActivity;
import com.markLove.Xplan.bean.BaseBean;
import com.markLove.Xplan.bean.GoNativeBean;
import com.markLove.Xplan.bean.GroupDetailBean;
import com.markLove.Xplan.bean.UserBean;
import com.markLove.Xplan.bean.msg.Message;
import com.markLove.Xplan.bean.msg.body.FileMessageBody;
import com.markLove.Xplan.bean.msg.body.TxtMessageBody;
import com.markLove.Xplan.config.Constants;
import com.markLove.Xplan.mvp.contract.ChatView;
import com.markLove.Xplan.mvp.contract.GroupChatContract;
import com.markLove.Xplan.mvp.presenter.ChatPresenter;
import com.markLove.Xplan.mvp.presenter.GroupChatPresenter;
import com.markLove.Xplan.mvp.presenter.impl.ChatPresenterImpl;
import com.markLove.Xplan.ui.adapter.GroupChatMessageAdapter;
import com.markLove.Xplan.ui.dialog.ExitRoomDialog;
import com.markLove.Xplan.ui.dialog.MorePopWindow;
import com.markLove.Xplan.ui.dialog.RemoveMsgDialog;
import com.markLove.Xplan.ui.dialog.ReportDialog;
import com.markLove.Xplan.ui.dialog.SignUpTipDialog;
import com.markLove.Xplan.utils.AudioUtils;
import com.markLove.Xplan.utils.DataUtils;
import com.markLove.Xplan.utils.DensityUtils;
import com.markLove.Xplan.utils.ImageLoaderUtils;
import com.markLove.Xplan.utils.LogUtils;
import com.markLove.Xplan.utils.PreferencesUtils;
import com.markLove.Xplan.utils.StatusBarUtil;
import com.markLove.Xplan.utils.ToastUtils;
import com.networkengine.controller.callback.ErrorResult;
import com.networkengine.controller.callback.RouterCallback;
import com.networkengine.controller.callback.XCacheCallback;
import com.networkengine.database.table.Member;
import com.networkengine.engine.LogicEngine;
import com.networkengine.entity.IMSendResult;
import com.networkengine.entity.MemEntity;
import com.networkengine.entity.RequestGetMembersParam;
import com.xsimple.im.control.IMChatLogic;
import com.xsimple.im.control.MessagerLoader;
import com.xsimple.im.control.iable.IIMChatLogic;
import com.xsimple.im.control.listener.IMChatCallBack;
import com.xsimple.im.db.DbManager;
import com.xsimple.im.db.datatable.IMChat;
import com.xsimple.im.db.datatable.IMGroupRemark;
import com.xsimple.im.db.datatable.IMMessage;
import com.xsimple.im.engine.IMEngine;
import com.xsimple.im.event.ExitGroupEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.OnCompressListener;

/**
 * 组局聊天室
 */
public class GroupChatActivity extends BaseActivity<GroupChatPresenter> implements View.OnClickListener, ChatView, GroupChatContract.View, IMChatCallBack {
    private ArrayList<Media> select;
    private FrameLayout mFlMore;
    private RecyclerView rlChatMsgList;
    private RelativeLayout mRlTitleBar;
    private RelativeLayout mRlCancel;
    private RelativeLayout mRlRemove;
    private com.markLove.Xplan.ui.widget.ChatView chatView;

    private TextView mTvJoinCount, mTvPlace, mTvTime, mTvRemark, mTvGroupName;
    private Button mBtnJoin;
    private TextView mTvInvitation; //邀请好友
    private ImageView mIvGroupHead;
    private RelativeLayout mRlHeads;

    ChatPresenter chatPresenter;
    GroupChatMessageAdapter chatMessageAdapter;
    GroupDetailBean.GroupBean groupBean;
    //    AutoCameraUtils autoCameraUtils;
    int me_user_id;
    int to_user_id;  //聊天室id
    int dataId; //组局id
    String chatId; //聊天室id
    int agreeMsgId;  //同意报名的消息id
    boolean keyboardIsShown = false;
    int usableHeightPrevious = 0;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_group_chat;
    }

    @Override
    public GroupChatPresenter onCreatePresenter() {
        return new GroupChatPresenter();
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        StatusBarUtil.setStatusBarColor(this, R.color.color_190827);
        rlChatMsgList = findViewById(R.id.chat_msg_list);
        mFlMore = findViewById(R.id.fl_more);
        mRlTitleBar = findViewById(R.id.rl_title_bar);
        mRlCancel = findViewById(R.id.rl_cancel);
        mRlRemove = findViewById(R.id.rl_remove);

        mTvJoinCount = findViewById(R.id.tv_jion_count);
        mTvPlace = findViewById(R.id.tv_place);
        mTvTime = findViewById(R.id.tv_time);
        mTvRemark = findViewById(R.id.tv_remark);
        mBtnJoin = findViewById(R.id.btn_enter);
        mTvInvitation = findViewById(R.id.tv_invitation);
        mRlHeads = findViewById(R.id.rl_heads);
        mIvGroupHead = findViewById(R.id.iv_group_head);
        mTvGroupName = findViewById(R.id.tv_group_name);
        mTvInvitation = findViewById(R.id.tv_invitation);

        findViewById(R.id.fl_more).setOnClickListener(this);
        findViewById(R.id.tv_cancel).setOnClickListener(this);
        findViewById(R.id.iv_remove).setOnClickListener(this);
        findViewById(R.id.btn_enter).setOnClickListener(this);
        findViewById(R.id.fl_back).setOnClickListener(this);
        findViewById(R.id.tv_invitation).setOnClickListener(this);
        mRlHeads.setOnClickListener(this);

        chatView = findViewById(R.id.chatView);
        chatView.setActivity(this);
        initData();
        setListener();
        initSoftKeyboard();
    }

    private void setListener() {
        chatView.setOnSendMessageListener(new com.markLove.Xplan.ui.widget.ChatView.OnSendMessageListener() {
            @Override
            public void onSendMessage(Message message) {
                if (message.getChatType() == Message.ChatType.TXT) {
                    TxtMessageBody txtMessageBody = (TxtMessageBody) message.getBody();
                    mImChatControl.sendMessage(IMMessage.CONTENT_TYPE_TXT, txtMessageBody.getMsg());
                } else {
                    FileMessageBody fileMessageBody = (FileMessageBody) message.getBody();
                    sendVoice(fileMessageBody.getFilePath());
                }
            }
        });

        rlChatMsgList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 0;
                outRect.right = 0;
                outRect.bottom = DensityUtils.dip2px(GroupChatActivity.this, 10f);
                outRect.top = DensityUtils.dip2px(GroupChatActivity.this, 10f);
            }
        });

        rlChatMsgList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                chatMessageAdapter.dismissItemPopup();
                closeSoftKeyBoard();
                chatView.hideView();
                return false;
            }
        });
        chatMessageAdapter.setOnOnAdapterCallBack(new GroupChatMessageAdapter.OnAdapterCallBack() {
            @Override
            public void onAgree(int userId, int msgId) {
                agreeMsgId = msgId;
                participateGroup(userId, dataId);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_more:
                showMoreDialog();
                break;
            case R.id.tv_cancel:
                cancelRemove();
                break;
            case R.id.iv_remove:
                showRemoveDialog();
                break;
            case R.id.btn_enter:
                if (groupBean != null) {
                    if (groupBean.getCurrentNum() == groupBean.getMaxNum()) {
                        ToastUtils.showLong(this, "该组局已满员");
                    } else {
                        applyGroup(groupBean.getId());
                    }
                }
                break;
            case R.id.rl_heads:
                startGroupMembersActivity();
                break;
            case R.id.tv_invitation:
                if (groupBean.getCurrentNum() == groupBean.getMaxNum()) {
                    ToastUtils.showLong(this, "该组局已满员");
                } else {
                    startInvitingFriendsActivity();
                }
                break;
            case R.id.fl_back:
                finish();
                break;
        }
    }

    private void startGroupMembersActivity() {
        Intent intent = new Intent(GroupChatActivity.this, GroupMembersActivity.class);
        intent.putExtra("dataId", dataId);
        startActivityForResult(intent, Constants.REQUEST_CODE_CHAT_MEMBER);
    }

    /**
     * 邀请好友
     */
    private void startInvitingFriendsActivity() {
        Intent intent = new Intent(GroupChatActivity.this, InvitingFriendsActivity.class);
        intent.putExtra("dataId", dataId);
        startActivity(intent);
    }

    protected void initData() {
        Intent intent = getIntent();
        to_user_id = intent.getIntExtra("chatId", 0);
        dataId = intent.getIntExtra("dataId", 0);
        me_user_id = PreferencesUtils.getInt(this, Constants.ME_USER_ID);
        LogUtils.d("me_user_id=" + me_user_id);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        chatMessageAdapter = new GroupChatMessageAdapter(this, new ArrayList<Message>());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
        String url = FileUtil.saveBitmap("haha", bitmap);
//        chatMessageAdapter.setToHeadImgUrl(headImgUrl);
        chatMessageAdapter.setToHeadImgUrl(url);
        chatMessageAdapter.setFromHeadImgUrl(PreferencesUtils.getString(this, Constants.ME_HEAD_IMG_URL));
        chatMessageAdapter.setFailMessageResend(failMessageResend);
        manager.setStackFromEnd(false);
        rlChatMsgList.setLayoutManager(manager);
        rlChatMsgList.setAdapter(chatMessageAdapter);

        chatMessageAdapter.registerAdapterDataObserver(adapterDataObserver);

        chatPresenter = new ChatPresenterImpl();
        chatPresenter.setView(this);
        chatView.setId(me_user_id, dataId);
//        tvChatSendPrice.setText(gold + "");
//        chatPresenter.getHistory(me_user_id, to_user_id);
        getGroupDetailData(dataId);
        joinGroup(dataId);
    }

    /**
     * 进入组局
     *
     * @param dataId
     */
    private void joinGroup(int dataId) {
        Map<String, String> map = new HashMap<>();
        map.put("groupId", String.valueOf(dataId));
        mPresenter.joinGroup(map);
    }

    /**
     * 退出组局
     *
     * @param
     */
    private void exitGroup() {
        Map<String, String> map = new HashMap<>();
        map.put("groupId", String.valueOf(dataId));
        mPresenter.exitGroup(map);
    }

    /**
     * 报名参加组局
     *
     * @param dataId
     */
    private void applyGroup(int dataId) {
        Map<String, String> map = new HashMap<>();
        map.put("groupId", String.valueOf(dataId));
        mPresenter.applyGroup(map);
    }

    /**
     * 同意报名
     *
     * @param targetUserId
     * @param dataId
     */
    private void participateGroup(long targetUserId, int dataId) {
        Map<String, String> map = new HashMap<>();
        map.put("targetUserId", String.valueOf(targetUserId));
        map.put("groupId", String.valueOf(dataId));
        mPresenter.participateGroup(map);
    }

    private void getGroupDetailData(int dataId) {
        Map<String, String> map = new HashMap<>();
        map.put("groupId", String.valueOf(dataId));
//        map.put("page", String.valueOf(1));
//        map.put("rows", String.valueOf(10));
        mPresenter.groupDetails(map);
    }

    /**
     * 报名
     */

    private void signUp() {
        SignUpTipDialog signUpTipDialog = new SignUpTipDialog(this);
        signUpTipDialog.setCanceledOnTouchOutside(true);
        signUpTipDialog.show();
    }

    RecyclerView.AdapterDataObserver adapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            rlChatMsgList.post(new Runnable() {
                @Override
                public void run() {
                    if (null != rlChatMsgList) {
                        rlChatMsgList.smoothScrollToPosition(chatMessageAdapter.getItemCount());
                    }
                }
            });
        }
    };

    GroupChatMessageAdapter.FailMessageResend failMessageResend = new GroupChatMessageAdapter.FailMessageResend() {
        @Override
        public void failResend(Message msg) {
            closeSoftKeyBoard();
        }
    };

    /**
     * 显示历史消息
     *
     * @param historyMessageList
     */
    @Override
    public void showHistoryMessage(final List<Message> historyMessageList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                chatMessageAdapter.addAll(historyMessageList);
            }
        });
    }

    @Override
    public void addOneMessage(Message msg) {

    }

    @Override
    public void updataMessage(final String msgID, final int errorCode) {
    }

    /**
     * 压缩图片
     * @param photos
     */
    private void compressImg(List<String> photos, boolean isOrigin){
        if (photos.size() == 0) return;
        if (isOrigin){
            for (String path:photos){
                sendImage(path);
            }
        } else {
            top.zibin.luban.Luban.with(this)
                    .load(photos)
                    .ignoreBy(100)
                    .setTargetDir(Constants.LOCAL_IMG_PATH) //缓存路径
                    .filter(new CompressionPredicate() {
                        @Override
                        public boolean apply(String path) {
                            return !(TextUtils.isEmpty(path) || path.toLowerCase().endsWith(".gif"));
                        }
                    })
                    .setCompressListener(new OnCompressListener() {
                        @Override
                        public void onStart() {
                        }

                        @Override
                        public void onSuccess(File file) {
                            sendImage(file.getAbsolutePath());
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                    }).launch();
        }
    }

    @Override
    public void onBackPressed() {
        if (keyboardIsShown || chatView.isShow()) {
            closeSoftKeyBoard();
            chatView.hideView();
        } else super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        ChatClient.getInstance().isNotification = false;
//        ChatClient.getInstance().currentlyID = to_user_id;
        Log.v("MAIN", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (null != chatPresenter) {
            chatPresenter.onResume();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
//        ChatClient.getInstance().isNotification = true;
        Log.v("MAIN", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        chatMessageAdapter.unregisterAdapterDataObserver(adapterDataObserver);
//        if (null != handler) {
//            handler.removeCallbacksAndMessages(null);
//        }
        if (null != chatPresenter) {
            chatPresenter.onDestory();
        }
        chatView.onDestroy();
        AudioUtils.getInstance().destory();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Log.i("huang", "requestCode=" + requestCode + "   resultCode=" + resultCode);
            if (requestCode == Constants.REQUEST_CODE_CAMERA) {
                final String path = data.getStringExtra("path");
                LogUtils.i("huang", "path=" + path);
                ArrayList<String> photos = new ArrayList<>();
                photos.add(path);
                compressImg(photos,false);
            }
            if (requestCode == Constants.REQUEST_CODE_PICKER) {
                select = data.getParcelableArrayListExtra(PickerConfig.EXTRA_RESULT);
                Boolean isOrigin = data.getBooleanExtra(PickerConfig.IS_ORIGIN, false);
                ArrayList<String> photos = new ArrayList<>();
                for (final Media media : select) {
                    LogUtils.i("media", media.toString());
                    if (new File(media.path).exists()){
                        photos.add(media.path);
                    }
                }
                compressImg(photos,isOrigin);
            }

            if (requestCode == Constants.REQUEST_CODE_CHAT_MEMBER) {
                GoNativeBean goNativeBean = (GoNativeBean) data.getSerializableExtra("goNativeBean");
                if (me_user_id == groupBean.getCreateBy() && goNativeBean.getCloseView() == 2) {
                    Intent intent = new Intent();
                    setResult(RESULT_OK, intent);
                    finish();
                } else if (goNativeBean.getCloseView() == 2) {
                    finish();
                }
            }
            if (resultCode == 102) {
                LogUtils.i("CJT", "video");
                String path = data.getStringExtra("path");
            }
            if (resultCode == 103) {
                Toast.makeText(this, "请检查相机权限~", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @TargetApi(23)
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == Constants.REQUEST_CODE_PERMISSION_ONE) {
            int size = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size++;
                }
                //相机权限
                int cameraPermissionResult = grantResults[2];
                boolean cameraPermissionGranted = cameraPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!cameraPermissionGranted) {
                    size++;
                }
                if (size == 0) {
                    startCameraActivity2();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }

        if (requestCode == Constants.REQUEST_CODE_PERMISSION_TWO) {
            int size2 = 0;
            if (grantResults.length >= 1) {
                int writeResult = grantResults[0];
                //读写内存权限
                boolean writeGranted = writeResult == PackageManager.PERMISSION_GRANTED;//读写内存权限
                if (!writeGranted) {
                    size2++;
                }
                //录音权限
                int recordPermissionResult = grantResults[1];
                boolean recordPermissionGranted = recordPermissionResult == PackageManager.PERMISSION_GRANTED;
                if (!recordPermissionGranted) {
                    size2++;
                }
                if (size2 == 0) {
//                    showRlRecord();
                    chatView.showRlRecord();
                } else {
                    Toast.makeText(this, "请到设置-权限管理中开启", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void startCameraActivity2() {
        Intent intent = new Intent(this, CameraActivity.class);
        intent.putExtra("type", JCameraView.BUTTON_STATE_ONLY_CAPTURE);
        startActivityForResult(intent, Constants.REQUEST_CODE_CAMERA);
    }

    /**
     * 显示更多弹窗
     */
    private void showMoreDialog() {
        MorePopWindow morePopWindow = new MorePopWindow(this, getString(R.string.report_group)
                , getString(R.string.exit_group));
        morePopWindow.showAtBottom(mFlMore);
        morePopWindow.setOnDialogCallBack(new MorePopWindow.OnDialogCallBack() {
            @Override
            public void onReportCallBack() {
                showReportDialog();
            }

            @Override
            public void onExitCallBack() {
                showExitTipDialog();
            }
        });
    }

    private void showExitTipDialog() {
        ExitRoomDialog exitRoomDialog = new ExitRoomDialog(this);
        exitRoomDialog.setOnDialogCallBack(new ExitRoomDialog.OnDialogCallBack() {
            @Override
            public void onCallBack(String content) {
                exitGroup();
            }
        });
        exitRoomDialog.show();
        exitRoomDialog.setTipContent(getString(R.string.exit_group_chat_room));
    }

    /**
     * 显示举报框
     */
    private void showReportDialog() {
        ReportDialog reportDialog = new ReportDialog(this);
        reportDialog.setOnDialogCallBack(new ReportDialog.OnDialogCallBack() {
            @Override
            public void onCallBack(String content) {
                toast(content);
            }
        });

        reportDialog.show();
    }

    //设置软键盘弹起和关闭的监听
    private void initSoftKeyboard() {
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int usableHeightNow = computeUsableHeight();
                if (usableHeightNow != usableHeightPrevious) {
                    int usableHeightSansKeyboard = getWindow().getDecorView().getHeight();
                    int heightDifference = usableHeightSansKeyboard - usableHeightNow;
                    if (heightDifference > (usableHeightSansKeyboard / 4)) {
                        keyboardIsShown = true;
                        chatView.hideView();
                    } else {
                        // 键盘收起
                        keyboardIsShown = false;
                    }
                    getWindow().getDecorView().requestLayout();
                    usableHeightPrevious = usableHeightNow;
                }
            }
        });
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(r);
        return (r.bottom - r.top);
    }

    private void closeSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 显示删除按钮
     */
    @Override
    public void showRemove() {
        closeSoftKeyBoard();
        chatView.setVisibility(View.GONE);
        chatView.hideView();
        mRlTitleBar.setVisibility(View.GONE);
        mRlCancel.setVisibility(View.VISIBLE);
        mRlRemove.setVisibility(View.VISIBLE);
    }

    PopupWindow removePopup;

    /**
     * 选择要删除的内容
     */
    public void showRemoveDialog() {
        if (!chatMessageAdapter.hasSelectRemovePostion()) {
            RemoveMsgDialog removeMsgDialog = new RemoveMsgDialog(this);
            removeMsgDialog.setOnMenuClickListener(new RemoveMsgDialog.OnMenuClickListener() {
                @Override
                public void onMenuClick() {
                    chatMessageAdapter.selectRemove(2);
                    if (null != removePopup) removePopup.dismiss();
                    mRlCancel.setVisibility(View.GONE);
                    mRlRemove.setVisibility(View.GONE);
                    mRlTitleBar.setVisibility(View.VISIBLE);
                    chatView.setVisibility(View.VISIBLE);
                }
            });
            removeMsgDialog.show();
        } else {
            ToastUtils.showCenter(this, "请选择要删除的内容", 0);
        }
    }

    /**
     * 取消删除
     */
    public void cancelRemove() {
        chatMessageAdapter.selectRemove(0);
        mRlCancel.setVisibility(View.GONE);
        mRlRemove.setVisibility(View.GONE);
        chatView.setVisibility(View.VISIBLE);
        mRlTitleBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void selectPosition(final int position) {
        rlChatMsgList.post(new Runnable() {
            @Override
            public void run() {
                rlChatMsgList.smoothScrollToPosition(position);
            }
        });
    }

    @Override
    public void onGroupDetail(GroupDetailBean detailBean) {
        groupBean = detailBean.getGroup();
        if (detailBean == null || groupBean == null) return;
        addGroupPersonHead(detailBean.getUserList());

        mTvPlace.setText(groupBean.getAddress());
        mTvGroupName.setText(groupBean.getTitle());
        mTvJoinCount.setText(groupBean.getCurrentNum() + "/" + groupBean.getMaxNum());
        mTvTime.setText(DataUtils.getDateTime2(groupBean.getStartTime()));
        String payType = "";
        if (groupBean.getPaymentType() == 1) {
            payType = "AA制";
        } else if (groupBean.getPaymentType() == 2) {
            payType = "你买单";
        } else {
            payType = "我买单";
        }
        String reachType = "";
        if (groupBean.getReachType() == 1) {
            reachType = "各自抵达";
        } else if (groupBean.getReachType() == 2) {
            reachType = "需要接送";
        } else {
            reachType = "负责接送";
        }
        String sex = "";
        if (groupBean.getSex() == 0) {
            sex = "不限";
        } else if (groupBean.getSex() == 1) {
            sex = "仅限男生";
        } else {
            sex = "仅限女生";
        }
        mTvRemark.setText(sex + "、" + payType + "、" + reachType);
        //未开始
        if (groupBean.getStatus() == 0) {
            //组局创建人
            if (me_user_id == groupBean.getCreateBy()) {
                mTvInvitation.setVisibility(View.VISIBLE);
                mBtnJoin.setVisibility(View.GONE);
            } else {
                mBtnJoin.setVisibility(View.VISIBLE);
                mTvInvitation.setVisibility(View.GONE);
//                //已报名
                if (groupBean.getUserStatus() == 1) {
                    mBtnJoin.setText(getString(R.string.joined));
                    mBtnJoin.setTextColor(getColor(R.color.color_333333));
                    mBtnJoin.setBackgroundResource(R.drawable.bg_joined);
                    mBtnJoin.setClickable(false);
                    //报名成功
                } else if (groupBean.getUserStatus() == 2) {
                    mBtnJoin.setText(getString(R.string.joined_success));
                    mBtnJoin.setTextColor(getColor(R.color.white));
                    mBtnJoin.setBackgroundResource(R.drawable.bg_enter);
                    mBtnJoin.setClickable(false);
                    //自己退出组局
                } else if (groupBean.getUserStatus() == 3) {
                    mBtnJoin.setText(getString(R.string.enter));
                    mBtnJoin.setTextColor(getColor(R.color.white));
                    mBtnJoin.setBackgroundResource(R.drawable.bg_enter);
                    mBtnJoin.setClickable(false);
                } else if (groupBean.getUserStatus() == 4) { //被踢出
                    mBtnJoin.setText("被踢出");
                    mBtnJoin.setClickable(false);
                }
            }
            //进行中
        } else if (groupBean.getStatus() == 1) {
            mBtnJoin.setText(getString(R.string.going));
            setBtnDisplayStatus();
            //已结束
        } else if (groupBean.getStatus() == 2) {
            mBtnJoin.setText(getString(R.string.finished));
            setBtnDisplayStatus();
            //已解散
        } else {
            mBtnJoin.setText(getString(R.string.dissolution));
            setBtnDisplayStatus();
        }
        String urlHead = detailBean.getUserList().get(0).getHeadImageUrl();
        ImageLoaderUtils.displayCircle(this, urlHead, mIvGroupHead);

        initIm(detailBean);
    }

    private void setBtnDisplayStatus() {
        mBtnJoin.setTextColor(getColor(R.color.white));
        mBtnJoin.setBackgroundResource(R.drawable.bg_joined);
        mBtnJoin.setClickable(false);
    }

    private void addGroupPersonHead(List<GroupDetailBean.UserListBean> userList) {
        if (userList != null && !userList.isEmpty()) {
            int size = userList.size() > 5 ? 5 : userList.size();
            for (int i = 0; i < size; i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.layout_head_item, null);
                ImageView imageHead = view.findViewById(R.id.chat_head_img);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.leftMargin = i * DensityUtils.dip2px(this, 26);
                view.setLayoutParams(layoutParams);
                if (i == size - 1) {
                    Glide.with(this).load(R.drawable.ic_more_head).into(imageHead);
                } else {
                    ImageLoaderUtils.displayCircle(this, userList.get(i).getHeadImageUrl(), imageHead);
                }
                mRlHeads.addView(view);
            }
        }
    }

    @Override
    public void onParticipateGroup(BaseBean baseBean) {
        if (baseBean.Status == 200) {
            IMMessage imMessage = DbManager.getInstance(this).loadIMMessageByLocalId(agreeMsgId);
            imMessage.setIsAgree(true);
            imMessage.update();
            chatMessageAdapter.refreshAgreeMessageStatus(agreeMsgId);
        } else {
            toast("同意报名失败!");
        }
    }

    @Override
    public void onApplyGroup(BaseBean baseBean) {
        if (baseBean.Status == 200) {
            mBtnJoin.setText(getString(R.string.joined));
            mBtnJoin.setTextColor(getColor(R.color.color_333333));
            mBtnJoin.setBackgroundResource(R.drawable.bg_joined);
            mBtnJoin.setClickable(false);
        } else {
            toast("申请加入组局失败!，请重新申请。");
        }
    }

    @Override
    public void onExitGroup(BaseBean baseBean) {
        if (baseBean.Status == 200) {
            exitChatRoom();
            finish();
        } else {
            toast("退出组局失败!");
        }
    }

    /**
     * 报名成功
     */
    public void showJoinSuccess() {
        mBtnJoin.setText(getString(R.string.joined_success));
        mBtnJoin.setTextColor(getColor(R.color.white));
        mBtnJoin.setBackgroundResource(R.drawable.bg_enter);
        mBtnJoin.setClickable(false);
    }


    /**
     * 聊天控制器
     */
    private IIMChatLogic mImChatControl;

    /**
     * Im　消息引擎
     */
    private IMEngine mImEngine;
    private MessagerLoader mMessagerLoader;
    /**
     * 会话
     */
    private IMChat mImChat;
    private List<IMMessage> mMessagesList;

    private static final String EXTRA_TARGET = "EXTRA_TARGET";
    private static final String EXTRA_SCROLL_TO_MESSAGE = "EXTRA_SCROLL_TO_MESSAGE";

    /**
     * 直接跳转到某条记录的消息virtualId
     */
    public static final String ACTION_SCROLL_TO_MESSAGE = "im_virtual_id";

    /**
     * 打开聊天界面
     *
     * @param context     上下文
     * @param memEntity   对象
     * @param scrollToMsg 打开界面同时跳转到指定消息
     */
    public static void startMe(Context context, MemEntity memEntity, String scrollToMsg, RouterCallback callback) {
//        routerCallback = callback;
        Intent intent = getStartIntent(context, memEntity);
        intent.putExtra(EXTRA_SCROLL_TO_MESSAGE, scrollToMsg);
        context.startActivity(intent);
    }

    /**
     * 获取跳转Intent
     *
     * @param context   上下文
     * @param memEntity 对象
     * @return 跳转Intent
     */
    public static Intent getStartIntent(Context context, MemEntity memEntity) {
        Intent intent = new Intent(context, ShopChatActivity.class);
        intent.putExtra(EXTRA_TARGET, memEntity);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    private void initIm(GroupDetailBean detailBean) {
        chatId = String.valueOf(getIntent().getIntExtra("chatId", 1));

        EventBus.getDefault().register(this);
        initMember();
        mImEngine = IMEngine.getInstance(this);
        //聊天对象
//        IMGroup group = mIMEngine.getIMGroup(id);
//        IMChatActivity.startMe(getContext(), new MemEntity(group.getId(), group.getName(), group.getType()), null, null);

//        final MemEntity memEntity = (MemEntity) getIntent().getSerializableExtra(EXTRA_TARGET);
        final MemEntity memEntity = new MemEntity(chatId, String.valueOf(dataId),detailBean.getGroup().getTitle(), IMChat.SESSION_GROUP_CLUSTER);
        mImChatControl = new IMChatLogic.Build() {
            @Override
            public MemEntity setTargetMem() {
                return memEntity;
            }

            @Override
            public IMChatCallBack setIMChatCallBack() {
                return GroupChatActivity.this;
            }


            @Override
            public int setRegistCode() {
                return IMEngine.IMENGINE_RIGIST_CODE_CHAT_ACYIVITY;
            }

            @Override
            public String setEventCode() {
                return IMEngine.EVENT_RIGIST_CODE_CHAT_ACYIVITY;
            }

        }.build(this);
        //异常处理
        if (mImChatControl == null) {
            Toast.makeText(this, "启动聊天页面失败", Toast.LENGTH_SHORT).show();
            LogUtils.e("聊天器控制器 is null");
            finish();
            return;
        }
        if (mImChatControl.getMyUid() == null) {
            LogUtils.e("启动聊天页面失败>>>>用户为空----> 自己");
            finish();
            return;
        }

//        if (memEntity.getType() != 0) {
//            IMGroup imGroup = mImChatControl.getIMGroup();
//            if (imGroup == null) {
//                LogUtils.e("启动聊天页面失败>>>>群主为空---->");
//                finish();
//                return;
//            }
//        }
        //设置草稿
//        IMChat imChat = mChatControl.getIMChat();
//        if (imChat != null) {
//            mImChatViewHolder.setDrafts(imChat.getDrafts());
//        }

        mImChat = mImChatControl.getIMChat();
        //刷新会话
        if (mImChat == null) {
            mImChat = mImChatControl.getOrCreateChat();
        }
        mMessagerLoader = new MessagerLoader(this, mImChat, mImChatControl);
        //未读数量
//        mUnReadCount = mImChat.getUnReadCount();
//        //获取未读和＠我的消息的本地id的集合
//        unReadOrAitMeIdlist = mMessagerLoader.getUnReadOrAitMeIdlist();
//        //修改消息的阅读状态 发送服务器请求
//        mImChatControl.updataUnReadCount(mMessagerLoader.getCacheMessage());
        //添加第一次加载的数据
        mMessagerLoader.getFirstLoadMessager();
        this.mMessagesList = mMessagerLoader.getShowMessage();
        chatMessageAdapter.setImChatControl(mImChatControl);
        //刷新布局
        chatMessageAdapter.setImData(mMessagesList);
        chatMessageAdapter.notifyDataSetChanged();
        chatView.initEvent(mImChatControl, mImEngine, mMessagerLoader);
        joinChatRoom();
    }

    private void initMember() {
        if (LogicEngine.getInstance().getUser() == null) {
            String userToken = PreferencesUtils.getString(this, PreferencesUtils.KEY_USER_TOKEN);
            UserBean userBean = App.getInstance().getUserBean();
            Member member = new Member();
            member.setId(String.valueOf(userBean.getUserInfo().getUserId()));
            member.setUserId(String.valueOf(userBean.getUserInfo().getUserId()));
            member.setUserName(userBean.getUserInfo().getNickName());
            member.setUserToken(userToken);
            LogicEngine.getInstance().setUser(member);
        }
    }

    /**
     * 加入聊天室，并订阅聊天室消息
     */
    private void joinChatRoom() {
        RequestGetMembersParam requestGetMembersParam = new RequestGetMembersParam(chatId);
        mImEngine.getIMController().joinChatRoom(requestGetMembersParam, new XCacheCallback<IMSendResult>() {
            @Override
            public void onLoaderCache(IMSendResult imSendResult) {

            }

            @Override
            public void onSuccess(IMSendResult result) {
                LogUtils.i("ShopChatTestActivity", "加入聊天室成功");
                mImEngine.subscribeToTopic(chatId);
            }

            @Override
            public void onFail(ErrorResult error) {
                //已经加入聊天室
                if(error.getCode() == 4097) {
                    mImEngine.subscribeToTopic(chatId);
                } else {
                    ToastUtils.showLong(GroupChatActivity.this,error.toString());
                }
                LogUtils.i("ShopChatTestActivity", "加入聊天室失败=" + error.toString());
            }
        });
    }

    /**
     * 退出聊天室，并取消订阅聊天室消息
     */
    private void exitChatRoom() {
        RequestGetMembersParam requestGetMembersParam = new RequestGetMembersParam(chatId);
        mImEngine.getIMController().ownQuitChatRoom(requestGetMembersParam, new XCacheCallback<IMSendResult>() {
            @Override
            public void onLoaderCache(IMSendResult imSendResult) {

            }

            @Override
            public void onSuccess(IMSendResult result) {
                LogUtils.i("ShopChatTestActivity", "退出聊天室成功");
                mImEngine.unsubscribeToTopic(chatId);
            }

            @Override
            public void onFail(ErrorResult error) {
                LogUtils.i("ShopChatTestActivity", "退出聊天室失败");
            }
        });
    }


    private void sendVoice(String path) {
        if (TextUtils.isEmpty(path) || !new File(path).exists()) {
            return;
        }
        List<String> list = new ArrayList<>();
        list.add(path);
        mImChatControl.uploadLocalFiles(list, IMMessage.CONTENT_TYPE_SHORT_VOICE);
    }

    /**
     * 发送照片
     */
    private void sendImage(String path) {
        if (TextUtils.isEmpty(path))
            return;
        File file = new File(path);
        if (!file.exists())
            return;
        mImChatControl.singUploadLocalFiles(path, IMMessage.CONTENT_TYPE_IMG);

//        if (IMMessage.CONTENT_TYPE_IMG.equals(type)) {
//
//        } else {
//            //压缩
////            compressVide(path, type);
//            // mChatControl.singUploadLocalFiles(path, type);
//        }
    }

    /**
     * 发送视频
     *
     * @param path
     */
    private void sendVideo(String path) {
        if (TextUtils.isEmpty(path))
            return;
        File file = new File(path);
        if (!file.exists())
            return;
        mImChatControl.singUploadLocalFiles(path, IMMessage.CONTENT_TYPE_VIDEO);
    }

    @Override
    public void setAtView(String atString) {

    }

    @Override
    public void showVoiceHFOrHook() {

    }

    @Override
    public void hideVoiceHFOrHook() {

    }

    @Override
    public void playNextMedia(int posstion) {

    }

    @Override
    public void changeSelectedMode(IMMessage imMessage, boolean isSelectedMode) {

    }

    @Override
    public void showGroupRemarkDialog(IMGroupRemark imGroupRemark) {

    }

    @Override
    public void onReply(IMMessage imMessage) {

    }

    @Override
    public void scrollToMsg(String msgId) {

    }

    @Override
    public void onFileTransferLoading(long localId) {

    }

    @Override
    public void onFileTransferSuccess(long localId) {
        LogUtils.i("huang", "onFileTransferSuccess=");
        chatMessageAdapter.refreshMessageStatus(localId, IMMessage.STATUS_SUCCESS, IMMessage.STATUS_SUCCESS);
    }

    @Override
    public void onFileTransferFailed(long localId) {
        LogUtils.i("huang", "onFileTransferFailed=");
        chatMessageAdapter.refreshMessageStatus(localId, IMMessage.STATUS_FAIL, IMMessage.STATUS_FAIL);
        ToastUtils.showLong(this, R.string.send_fail);
    }

    @Override
    public void onFileTransferOnPause(long localId) {

    }

    @Override
    public void onFileTransferOnStart(long localId) {

    }

    @Override
    public void onSendMessageSuccessCallBack(long localId) {
        LogUtils.i("huang", "onSendMessageSuccessCallBack=");
        chatMessageAdapter.refreshMessageStatus(localId, IMMessage.STATUS_SUCCESS, IMMessage.STATUS_SUCCESS);
    }

    @Override
    public void onSendMessageFaileCallBack(long localId) {
        LogUtils.i("huang", "onSendMessageFaileCallBack=");
        chatMessageAdapter.refreshMessageStatus(localId, IMMessage.STATUS_FAIL, IMMessage.STATUS_DEFAULT);
        ToastUtils.showLong(this, R.string.send_fail);
    }

    @Override
    public void onAddMessagerCallBack(List<IMMessage> msgs) {
        LogUtils.i("huang", "onAddMessagerCallBack=" + msgs.toString());
        onRefresfItemAddList(msgs);
    }

    @Override
    public void onAddMessagerCallBack(IMMessage message) {
        LogUtils.i("huang", "onAddMessagerCallBack2=" + message.toString());
        message.refresh();
        mMessagerLoader.addMessager(message);
        chatMessageAdapter.addOneImData(message);
    }

    @Override
    public void onDeleteMessageCallBack(IMMessage message) {
        chatMessageAdapter.deleteMessage(message.getLocalId());
    }

    @Override
    public void onUpdateMessageCallBack(long localId) {

    }

    @Override
    public void onDeleteMessagesCallback(List<IMMessage> imMessageList) {

    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onTranspondMsgToCurrent(TranspondToCurrentEvent transpondToCurrentEvent) {
//        //mProcessorFactory.processorAddMsg(transpondToCurrentEvent.getMessage());
//        mImChatViewHolder.onRefresfItemAdd(transpondToCurrentEvent.getMessage());
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onTranspondMsgToCurrentSuccess(TranspondToCurrentSuccessEvent event) {
//        // mProcessorFactory.processorUpdateMsg(event.getOld(), event.getNew());
//        mImChatViewHolder.onRefreshItem(event.getOld().getLocalId());
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void exitGroup(ExitGroupEvent e) {
        //删除会话
        IMChat imChat = mImChatControl.getIMChat();
        if (imChat != null) {
            imChat.delete();
        }
        finish();
    }

    public void onRefresfItemAddList(List<IMMessage> list) {
        if (list == null || list.isEmpty())
            return;
        //刷新会话
        if (mImChat == null) {
            mImChat = mImChatControl.getOrCreateChat();
        }
        mImChat.refresh();
        mImChatControl.updataUnReadCount(list);
        addItem(list, true);
    }

    /**
     * 添加视图
     */
    private void addItem(List<IMMessage> list, boolean isBottom) {
        if (list == null || list.isEmpty())
            return;

        mMessagerLoader.addMessager(list, isBottom);
        chatMessageAdapter.addListImData(list);

//        if (isBottom) {
//            mLinearLayoutManager.scrollToPositionWithOffset(0, 100);
//        }

    }

}
