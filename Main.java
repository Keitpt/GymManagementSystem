package gymmanagementsystem;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MemberService memberService = new MemberService();
        ServiceTrainer trainerService = new ServiceTrainer();
        PackageService packageService = new PackageService();
        ScheduleService scheduleService = new ScheduleService();
        PaymentService paymentService = new PaymentService();

        while (true) {
            System.out.println("__________________________________");
            System.out.println("|---  GYM MANAGEMENT SYSTEM   ---|");
            System.out.println("|1.Add Member--------------------|");
            System.out.println("|2.Update Member-----------------|");
            System.out.println("|3.Delete Member-----------------|");
            System.out.println("|4.Search Member by Name---------|");
            System.out.println("|5.Add Trainer-------------------|");
            System.out.println("|6.Update Trainer----------------|");
            System.out.println("|7.Delete Trainer----------------|");
            System.out.println("|8.Add Package-------------------|");
            System.out.println("|9.Update Package----------------|");
            System.out.println("|10.Delete Package---------------|");
            System.out.println("|11.Add Schedule-----------------|");
            System.out.println("|12.Add Payment------------------|");
            System.out.println("|13.Get Total Revenue by Month---|");
            System.out.println("|14.Exit-------------------------|");
            System.out.println("__________________________________\n");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Add Member
                    System.out.print("Enter name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter phone: ");
                    String memberPhone = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String memberEmail = scanner.nextLine();
                    System.out.print("Enter package ID: ");
                    int packageId = scanner.nextInt();
                    Member member = new Member(memberName, memberPhone, memberEmail, packageId);
                    memberService.addMember(member);
                    System.out.println("Member added successfully!");
                    break;

                case 2: // Update Member
                    System.out.print("Enter member ID to update: ");
                    int updateMemberId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new phone: ");
                    String newPhone = scanner.nextLine();
                    System.out.print("Enter new email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Enter new package ID: ");
                    int newPackageId = scanner.nextInt();
                    Member updatedMember = new Member(newName, newPhone, newEmail, newPackageId);
                    updatedMember.setMemberId(updateMemberId);
                    memberService.updateMember(updatedMember);
                    System.out.println("Member updated successfully!");
                    break;

                case 3: // Delete Member
                    System.out.print("Enter member ID to delete: ");
                    int deleteMemberId = scanner.nextInt();
                    memberService.deleteMember(deleteMemberId);
                    System.out.println("Member deleted successfully!");
                    break;

                case 4: // Search Member by Name
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    List<Member> foundMembers = memberService.searchMembersByName(searchName);
                    System.out.println("Found Members:");
                    for (Member m : foundMembers) {
                        System.out.println("ID: " + m.getMemberId() + ", Name: " + m.getName() + ", Phone: " + m.getPhone() + ", Email: " + m.getEmail());
                    }
                    break;

                case 5: // Add Trainer
                    System.out.print("Enter trainer name: ");
                    String trainerName = scanner.nextLine();
                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine();
                    Trainer trainer = new Trainer(trainerName, specialization);
                    trainerService.addTrainer(trainer);
                    System.out.println("Trainer added successfully!");
                    break;

                case 6: // Update Trainer
                    System.out.print("Enter trainer ID to update: ");
                    int updateTrainerId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new name: ");
                    String newTrainerName = scanner.nextLine();
                    System.out.print("Enter new specialization: ");
                    String newSpecialization = scanner.nextLine();
                    Trainer updatedTrainer = new Trainer(newTrainerName, newSpecialization);
                    updatedTrainer.setTrainerId(updateTrainerId);
                    trainerService.updateTrainer(updatedTrainer);
                    System.out.println("Trainer updated successfully!");
                    break;

                case 7: // Delete Trainer
                    System.out.print("Enter trainer ID to delete: ");
                    int deleteTrainerId = scanner.nextInt();
                    trainerService.deleteTrainer(deleteTrainerId);
                    System.out.println("Trainer deleted successfully!");
                    break;

                case 8: // Add Package
                    System.out.print("Enter package name: ");
                    String packageName = scanner.nextLine();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter duration (in months): ");
                    int duration = scanner.nextInt();
                    Package pkg = new Package(packageName, price, duration);
                    packageService.addPackage(pkg);
                    System.out.println("Package added successfully!");
                    break;

                case 9: // Update Package
                    System.out.print("Enter package ID to update: ");
                    int updatePackageId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter new package name: ");
                    String newPackageName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    double newPrice = scanner.nextDouble();
                    System.out.print("Enter new duration (in months): ");
                    int newDuration = scanner.nextInt();
                    Package updatedPackage = new Package(newPackageName, newPrice, newDuration);
                    updatedPackage.setPackageId(updatePackageId);
                    packageService.updatePackage(updatedPackage);
                    System.out.println("Package updated successfully!");
                    break;

                case 10: // Delete Package
                    System.out.print("Enter package ID to delete: ");
                    int deletePackageId = scanner.nextInt();
                    packageService.deletePackage(deletePackageId);
                    System.out.println("Package deleted successfully!");
                    break;

                case 11: // Add Schedule
                    System.out.print("Enter member ID: ");
                    int memberIdForSchedule = scanner.nextInt();
                    System.out.print("Enter trainer ID: ");
                    int trainerIdForSchedule = scanner.nextInt();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String dateString = scanner.next();
                    Date date = Date.valueOf(dateString);
                    System.out.print("Enter time slot (HH:MM:SS): ");
                    String timeString = scanner.next();
                    Time timeSlot = Time.valueOf(timeString);
                    Schedule schedule = new Schedule(memberIdForSchedule, trainerIdForSchedule, date, timeSlot);
                    scheduleService.addSchedule(schedule);
                    System.out.println("Schedule added successfully!");
                    break;

                case 12: // Add Payment
                    System.out.print("Enter member ID for payment: ");
                    int memberIdForPayment = scanner.nextInt();
                    System.out.print("Enter amount: ");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter date (YYYY-MM-DD): ");
                    String paymentDateString = scanner.next();
                    Date paymentDate = Date.valueOf(paymentDateString);
                    Payment payment = new Payment(memberIdForPayment, amount, paymentDate);
                    paymentService.addPayment(payment);
                    System.out.println("Payment added successfully!");
                    break;

                case 13: // Get Total Revenue by Month
                    System.out.print("Enter month (1-12): ");
                    int month = scanner.nextInt();
                    System.out.print("Enter year: ");
                    int year = scanner.nextInt();
                    double totalRevenue = paymentService.getTotalRevenueByMonth(month, year);
                    System.out.println("Total revenue for " + month + "/" + year + ": " + totalRevenue);
                    break;

                case 14: // Exit
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}