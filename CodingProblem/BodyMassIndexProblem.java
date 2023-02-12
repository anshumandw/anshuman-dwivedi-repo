import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

 

public class BodyMassIndexProblem {

 

		public static void main(String[] args) {
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt();
			List<Person> personList = new ArrayList<>();
			
			while(n != 0) {
                int height = sc.nextInt();
                int weight = sc.nextInt();
                double age = sc.nextDouble();
                personList.add(new Person(height, weight, age));
                --n;
			}
			
			System.out.println(highestAverageBMI(personList));
			
		}
		
		static String highestAverageBMI(List<Person> person) {
			String ageGroup = "";
			double averageBmi = 0;
			
			Collections.sort(person, new Comparator<Person>() {
				@Override
				public int compare(Person o1, Person o2) {
					if(o1.getAge() > o2.getAge()) {
						return 1;
					} else if(o1.getAge() < o2.getAge()) {
						return -1;
					} else return 0;
				}
			});

			Map<List<Double>, List<Double>> map = new HashMap<>();
			
			int minAge = (int) person.get(0).getAge();
			int maxAge = (int) person.get(person.size()-1).getAge();                               

			while(maxAge%5 != 0) {
				maxAge += 1;
			}
			while(minAge%5 != 0) {
				minAge -= 1;
			}

			double formatMaxAge = Math.round((maxAge - 0.1) * 10) / 10.0;                               

			List<List<Double>> ageGroupArray = new ArrayList<>();                               

			while(minAge != maxAge) {
				List<Double> addValue = new ArrayList<>();
				addValue.add((double)minAge);
				addValue.add(Math.round((minAge + 4.9) * 10) / 10.0);
				ageGroupArray.add(addValue);
				minAge += 5;
			}

			for(int j = 0; j<map.size(); j++) {
				for(int i=0; i<person.size(); i++) {
					double averageBmiPerPerson = getAverageBMI(person.get(i).getHeight(), person.get(i).getWeight());
					boolean checkAgeGroup = ageGroupArray.get(j).get(0) <= person.get(i).getAge() && person.get(i).getAge() <= ageGroupArray.get(j).get(1);

					if(ageGroupArray.get(j).get(1) < person.get(i).getAge()) {
						break;
					}
					
					if(map.containsKey(ageGroupArray.get(j)) && checkAgeGroup) {
						map.get(ageGroupArray.get(j)).add(averageBmiPerPerson);
					}
					
					else if(!checkAgeGroup) {
						for(int k=1; k<person.size(); k++) {
							boolean checkAgeGroupForK = ageGroupArray.get(j).get(0) <= person.get(k).getAge() && person.get(i).getAge() <= ageGroupArray.get(j).get(1);
							if(checkAgeGroupForK) {
								i = k - 1;
								break;
							}
						}
					} else {
						map.put(ageGroupArray.get(j), new ArrayList<>());
						map.get(ageGroupArray.get(j)).add(averageBmiPerPerson);
					}
				}
			}                              

			for(List<Double> mapList: map.values()) {
				double sumAverageBMI = 0;
				for(Double d: mapList) {
					sumAverageBMI += d;
				}				
				
				averageBmi = Math.max((sumAverageBMI / mapList.size()), averageBmi);
			}
			
			averageBmi = Math.round(averageBmi * 100) / 100.0;                

			System.out.println(ageGroupArray);

//			System.out.println(minAge + " " + formatMaxAge);
			
			return "{ageGroup: \"" + ageGroup + "\", averageBmi: " +  String.valueOf(averageBmi) + "}";

		}

		 static double getAverageBMI(int height, int weight) {
         	double ans = (weight / (height) * height) * 10000;
         	return Math.round(ans * 100) / 100.0;
         }           

}

 

class Person {
	
	private int height;
	private int weight;
	private double age;
	
	Person(int height, int weight, double age) {
    	this.height = height;
    	this.weight = weight;
    	this.age = age;
    }
	
	public int getHeight() {
        return height;
	}
	
	public int getWeight() {
        return weight;
	}

	public double getAge() {
    	return age;
    }               

}