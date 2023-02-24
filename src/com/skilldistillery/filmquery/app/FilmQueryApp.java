package com.skilldistillery.filmquery.app;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();

		//app.test();

		app.launch();
	}

	private void test() {
		// test for retrieving a film
		try {
			Film film = db.findFilmById(1);
			System.out.println(film);
			for(Actor a : film.getActors()) {
				System.out.println(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// test for retrieving an actor
//		try {
//			Actor anActor = db.findActorById(2);
//			System.out.println(anActor);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
		//test for list of actors by film id
//		try {
//			List<Actor> actors = db.findActorsByFilmId(1);
//			for(Actor a : actors) {
//				System.out.println(a);
//			}
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private void launch() {
		System.out.println("APP started.");
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
		System.out.println("APP ended.");
	}

	private void startUserInterface(Scanner input) {
		boolean inMainMenu = true;
		while(inMainMenu) {
			showMainMenu();
			//input
			int choice = 0;
			try {
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer value.");
			}
			finally {
				//clear SCANNER!!!
				//or else endless loop!
				input.nextLine();	
				}
			
			//process option
			switch(choice) {
			case 1:
				System.out.println("ID LOOKUP");
				break;
			case 2:
				System.out.println("KEYWORD LOOCKUP");
				break;
			case 3:
				System.out.println("GOODBYE");
				inMainMenu = false;
				break;
				
			}
		}

	}
	
	private void showMainMenu() {
		System.out.println("1. Look up film my ID number");
		System.out.println("2. Look up a film by a keyword");
		System.out.println("3. Exit");
		System.out.println("What would you like to do?");
	}

}


