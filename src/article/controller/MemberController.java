package article.controller;

import java.util.List;
import java.util.Scanner;

import article.dto.Member;
import article.util.Util;

public class MemberController {
	public static int lastMemberId = 0;
	private List<Member> members;
	private Scanner sc;
	public MemberController(Scanner sc, List<Member> members){
		this.sc = sc;
		this.members = members;
	}
	public void doList() {
		System.out.println("멤버 리스트 보기 입니다.");
		if(members.size() == 0) {
			System.out.println("등록된 멤버가 없습니다.");
		}else {
			System.out.println("회원번호 |    아이디    |   비밀번호   |     이름     |     등록일");
			for (int i = 0 ; i < members.size(); i++) {
				System.out.printf("%4d | %10s | %10s | %10s | %s%n", members.get(i).id, members.get(i).loginId, members.get(i).loginPw, members.get(i).userName, members.get(i).regDate );
			}
		}
	}
	
	public void doJoin() {
		int id = lastMemberId +1;
		lastMemberId = id;
		
		String loginId;
		String loginPw;
		String confirmPw;
		String regDate = Util.getRegDate();
		
		
		while(true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine();
			
			if(isJoinableLoginId(loginId) == false) {
				System.out.printf("%s는 이미 가입된 아이디입니다.", loginId);
				continue;
			}
			if(loginId.length() < 6) {
				System.out.println("아이디는 6글자 이상 입력해주세요.");
				continue;
			}else {
				break;
			}
			
		}
		while(true) {
			System.out.printf("비밀번호 : ");
			loginPw = sc.nextLine();
			if(loginPw.length() < 8) {
				System.out.println("비밀번호는 8글자 이상 입력해주세요.");
				continue;
			}
			System.out.printf("비밀번호 확인 : ");
			confirmPw = sc.nextLine();
			if(loginPw.equals(confirmPw)){
				break;
			}else {
				System.out.println("같은 비밀번호를 입력해주세요");
				continue;
			}
			
		}	
		System.out.printf("이름 : ");
		String userName = sc.nextLine();
			
		Member member = new Member(id, loginId, loginPw, regDate, userName);
		members.add(member);
		System.out.printf("%s 번째 회원이 가입되었습니다.%n", id);
	}
	public void doDelete(String foundId) {
		boolean found = false;
		
		for (int i = 0; i < members.size(); i++) {
			if (foundId.equals(members.get(i).loginId)) {
					members.remove(i);
					found = true;
					System.out.printf("%s 멤버 삭제하였습니다.%n", foundId);
					break;
			}
		}
		if (found == false) {
			System.out.printf("%s 멤버를 찾을 수 없습니다.%n", foundId);
		}

	}
	public void doModify(String foundId) {
		Member foundMember = null;
		for(int i = 0 ; i < members.size(); i++) {
			if (foundId.equals(members.get(i).loginId)) {
				System.out.printf("%s 아이디의 멤버를 찾았습니다.%n", foundId);
				foundMember = members.get(i);
		
				String loginPw;
				String confirmPw;
			
				while(true) {
					System.out.printf("변경할 비밀번호 : ");
					loginPw = sc.nextLine();
					if(loginPw.length() < 8) {
						System.out.println("비밀번호는 8글자 이상 입력해주세요.");
						continue;
					}
					System.out.printf("비밀번호 확인 : ");
					confirmPw = sc.nextLine();
					if(loginPw.equals(confirmPw)){
					
						break;
					}else {
						System.out.println("같은 비밀번호를 입력해주세요");
						continue;
					}
					
				}

				foundMember.loginPw = loginPw;
				System.out.printf("%s 멤버의 정보를 수정 완료 되었습니다.%n", foundId);
			}
		
		}
	}
	// 회원 로그인 아이디가 가입되어 있는 회원인지 찾기
		private boolean isJoinableLoginId(String loginId) {
		int index = getMemberIndexByLoginId(loginId);
		if (index == -1) { // 일치하는 index가 없는 경우
			return true;
		}
		return false;
	}
	// 회원 로그인 아이디로 인덱스 찾기
	private int getMemberIndexByLoginId(String loginId) {
		int i = 0;
		for (Member member : members) {
			if (member.loginId.equals(loginId)) {
				return i;
			}
			i++;
		}
		
		return -1;
	}
	
	
}
