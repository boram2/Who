package com.who.onecupafterwork.data;

public class ResultData {
	public String result; // 결과
	public String message; // 원인

	public String uphoto; // 본인 사진의 경로
	public String aphotoB; // 친구 사진의 경로
	public String aidB; // 친구의 아이디
	public String acode; // 그룹 번호

	// 3팀의 이성 목록
	public Contact[] groupID;

	/* 매칭 성공 시 받는 데이터 */
	public String mcode;
	public String mid;
	public String mname;
	public String mphoto;
	public String mphon;
	public String midF;
	public String mnameF;
	public String mphotoF;

	/* 프로필 보기 */
	public String upreligion;
	public String uparea;
	public String upfacebook;
	public String upintro;
	public String uphobby;
	public String upinterest;
	public String upblood;
	public String uscore;

	// 공지사항
	public String tolnum;
	public String title;
	public String time;
	public String readnum;

	public String content;

	// 매칭 히스토리
	public String mdate;
	public String marea;
	// public String mid;
	// public String mphoto;
	// public String midF;
	// public String mphotoF;

	// 친구 목록
	public FriendList[] data;
	// public String[] thumbnail;

	// 공지사항
	public Message[] notylist;

	// 친구요청 push날리는거 성공하면 오는 데이터
	public String anameB;
	// public String aphotoB;

	// 차단친구 리스트
	public BanNumList[] inqBlockPhoneNum;

	public String uprocess;

	// 초대 받은 사람의 추가 데이터
	public InvitedPerson[] uprocessData; 
}
