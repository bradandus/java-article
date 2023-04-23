package article.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import article.dto.Article;
import article.util.Util;

public class ArticleController {
	public static int lastArticleId = 0;
	public static List<Article> articles;
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

}
