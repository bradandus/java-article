package article;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("== 게시만 판들기 시작");
		Scanner sc = new Scanner(System.in);
		System.out.print("명령어 : ");
		String command = sc.nextLine();
		System.out.printf("입력된 명령어 : %s%n", command);
		
		sc.close();
	}

}
