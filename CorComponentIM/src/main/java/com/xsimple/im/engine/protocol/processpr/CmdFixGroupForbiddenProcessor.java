package com.xsimple.im.engine.protocol.processpr;

import android.content.Context;

import com.networkengine.entity.GroupMember;
import com.xsimple.im.R;
import com.xsimple.im.db.DbManager;
import com.xsimple.im.engine.protocol.MsgEntity;

import java.util.List;

/**
 * Created by pengpeng on 17/5/8.
 */

public class CmdFixGroupForbiddenProcessor extends CmdGroupExitProcessor {
    public CmdFixGroupForbiddenProcessor(Context mCt, DbManager mDbManager) {
        super(mCt, mDbManager);
    }

    @Override
    protected String getMsgContent(MsgEntity msgEntity, List<GroupMember> member) {
        String groupName = getGname(msgEntity);
        return String.format(mCt.getString(R.string.im_group_disabled), groupName);
    }

    protected boolean containsSelf(MsgEntity msgEntity) {
        return true;
    }

    @Override
    protected boolean addUpdateOrDelete(MsgEntity msgEntity) {

        return false;
    }
}
