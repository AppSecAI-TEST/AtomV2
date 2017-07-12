/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tongxun.atongmu.parent.ui.im;


import com.hyphenate.easeui.EaseConstant;

public class Constant extends EaseConstant {
	public static final String NEW_FRIENDS_USERNAME = "item_new_friends";
	public static final String GROUP_USERNAME = "item_groups";
	public static final String CHAT_ROOM = "item_chatroom";
	public static final String ACCOUNT_REMOVED = "account_removed";
	public static final String ACCOUNT_CONFLICT = "conflict";
	public static final String ACCOUNT_FORBIDDEN = "user_forbidden";
	public static final String CHAT_ROBOT = "item_robots";
	public static final String MESSAGE_ATTR_ROBOT_MSGTYPE = "msgtype";
	public static final String ACTION_GROUP_CHANAGED = "action_group_changed";
	public static final String ACTION_CONTACT_CHANAGED = "action_contact_changed";

    // 设置消息中 msgId 扩展的 key
    public static final String EM_ATTR_MSG_ID = "msg_id";
    // 设置自己扩展的消息类型的 key
    public static final String EM_ATTR_TYPE = "msg_type";
    public static final String EM_ATTR_TYPE_REVOKE = "revoke";

	//红包相关常量值
	public static final String REFRESH_GROUP_MONEY_ACTION = "refresh_group_money_action";
	public static final String EXTRA_LUCKY_MONEY_SENDER_ID = "money_sender_id";
	public static final String EXTRA_LUCKY_MONEY_RECEIVER_ID = "money_receiver_id";
	public static final String MESSAGE_ATTR_IS_OPEN_MONEY_MESSAGE = "is_open_money_msg";
	public static final String EXTRA_LUCKY_MONEY_SENDER_NAME = "money_sender";
	public static final String EXTRA_LUCKY_MONEY_RECEIVER_NAME = "money_receiver";

//	public static final String BASE_URL = "http://114.55.128.215:10010/backwork/rest/kig/";
	public static final String BASE_URL = "http://www.atongmu.net:10010/backwork/rest/kig/";
	public static final String CHAT_URL = BASE_URL + "restGetIMMsg";
	public static final String WIZARD_URL = BASE_URL + "restGetWizardList";
	public static final String RELATION_URL = BASE_URL + "restGetRelationList";
	public static final String ADDNEWPERSON_URL = BASE_URL + "restAddnewRelationPerson_v2";
	public static final String VIDEO_URL = BASE_URL + "restGetCameraInfo";
	public static final String CAR_INFO = BASE_URL + "restGetCarInfo";
	public static final String CAR_POSITION = "https://www.atongmu.net:8443/backwork/rest/kig/restGetCarPosition_v2";

//	public static final String CAR_INFO = "http://192.168.0.199:10010/backwork/rest/kig/restGetCarInfo";
//	public static final String CAR_POSITION = "http://192.168.0.199:10010/backwork/rest/kig/restGetCarPosition";
}
