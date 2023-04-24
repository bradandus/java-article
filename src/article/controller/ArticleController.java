package article.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.dto.Article;
import article.util.Util;

public class ArticleController {
	public static int lastArticleId = 0;
	public List<Article> articles;
	private Scanner sc;
	public ArticleController(Scanner sc, List<Article> articles){
		this.sc = sc;
		this.articles = articles;
	}
	
	public void doWrite() {

		int id = lastArticleId + 1;
		lastArticleId = id;
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();
		String regDate = Util.getRegDate();
		Article article = new Article(id, title, body, regDate);
		articles.add(article);
		System.out.printf("%d번째 글이 생성되었습니다.%n", id);
		System.out.printf("제목 : %s%n", title);
		System.out.printf("내용 : %s%n", body);
	}

	public void doList(String command) {

		System.out.println("게시물리스트 보기입니다.");
		String searchKeyword = command.substring("article.list".length()).trim();
		System.out.println("검색어 : "+ searchKeyword);
		List<Article> forListArticle = articles; // 전체 리스트 복제
		if(searchKeyword.length() > 0) { // 검색어가 있다면;
			forListArticle = new ArrayList<>(); // 검색어가 있으면 한번 초기화
			for(Article article : articles) {
				if (article.title.contains(searchKeyword) ) {
					forListArticle.add(article);// 검색어가 있는거만 계속 애
				}
			}
			if (forListArticle.size() == 0) {
				System.out.println("일치하는 검색결과가 없습니다.");
				System.out.println("전체 리스트를 보여줍니다.");
				forListArticle = articles; // 다시 전체 덮어씌우기
			}
		}else {
			System.out.println("검색어가 없습니다. 전체 리스트를 보여줍니다.");
		}
		System.out.println("번호  |       제목       |        작성/수정일      | 조회수 ");
		for (int i = 0; i < forListArticle.size(); i++) {
			System.out.printf("%2d  | %16s | %s | %d%n", forListArticle.get(i).id, forListArticle.get(i).title, forListArticle.get(i).regDate, forListArticle.get(i).hit);
		}
	}

	public void doDetail(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null){
			System.out.printf("%d 번째 게시물을 찾을 수 없습니다.%n", id);
		}else {
			foundArticle.increasHit();
			System.out.printf("번호 : %d%n", foundArticle.id);
			System.out.printf("제목 : %s%n", foundArticle.title);
			System.out.printf("내용 : %s%n", foundArticle.body);
			System.out.printf("등록 시간 : %s%n", foundArticle.regDate);
			System.out.printf("조회수 : %d%n", foundArticle.hit);
		}
		
	}
	
	public void doDelete(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		int foundIndex = getArticleIndexById(id);
		if (foundIndex == -1) {
			System.out.printf("%d 번 게시물을 찾을 수 없습니다.%n", id);
		}else {
			articles.remove(foundIndex);
			System.out.printf("%d 번 게시물을 삭제했습니다.%n", id);
		}
		
	}
	
	public void doModify(String command) {
		String[] commandBits = command.split(" ");
		int id = Integer.parseInt(commandBits[2]);
		Article foundArticle = getArticleById(id);
		if (foundArticle == null){
			System.out.printf("%d 번째 게시물을 찾을 수 없습니다.", id);
		}else {
			System.out.printf("%d 번 게시물을 찾았습니다.%n", id);
			System.out.printf("수정할 제목 : ");
			foundArticle.title = sc.nextLine();
			System.out.printf("수정할 내용 : ");
			foundArticle.body = sc.nextLine();
			foundArticle.regDate = Util.getRegDate();
			System.out.printf("%d 게시물이 수정 완료 되었습니다.%n", id);
			
		}
	}

	
	// 게시물 아이디로 찾기
	private Article getArticleById(int id) {
		int index = getArticleIndexById(id);
		if (index != -1) { // 일치하는 index가 있는 경우 (-1은 찾지 못한 경우)
			return articles.get(index);
		}
		return null;
	}
	
	// 게시물 아이디로 인덱스 찾기
	private int getArticleIndexById(int id) {
		int i = 0;
		for (Article article : articles) {
			if (article.id == id) {
				return i;
			}
			i++;
		}
		
		return -1;
	}

}
