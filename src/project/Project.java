/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author sadiq
 */
public class Project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        List<Person> people = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("MOCK_DATA.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0].trim());
                    String firstName = data[1].trim();
                    String lastName = data[2].trim();
                    String email = data[3].trim();
                    people.add(new Person(id, firstName, lastName, email));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Team> teams = generateTeams(people, 20, 5);
        for (Team team : teams) {
            System.out.println("Team: " + team.name);
            for (Person member : team.members) {
                System.out.println("  - " + member.firstName + " " + member.lastName);
            }
        }
    }

    private static List<Team> generateTeams(List<Person> people, int numTeams, int teamSize) {
        List<Team> teams = new ArrayList<>();
        List<Person> availablePeople = new ArrayList<>(people);

        for (int i = 1; i <= numTeams; i++) {
            Team team = new Team("Team " + i);
            Set<Person> teamMembers = new HashSet<>();

            while (teamMembers.size() < teamSize && !availablePeople.isEmpty()) {
                Random rand = new Random();
                int randomIndex = rand.nextInt(availablePeople.size());
                Person selectedPerson = availablePeople.get(randomIndex);
                teamMembers.add(selectedPerson);
                availablePeople.remove(randomIndex);
            }

            for (Person member : teamMembers) {
                team.addMember(member);
            }

            teams.add(team);
        }

        return teams;
    }
}
    
    

