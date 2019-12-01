package day04.code_6;

import java.util.concurrent.Phaser;

public class MyPhaser extends Phaser {

    @Override
    //phase为当前阶段，registeredParties为注册数
    protected boolean onAdvance(int phase, int registeredParties) {
        //根据不同的阶段调用不同的方法
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean studentsArrived() {
        //打印学生均到达考场的信息
        System.out.println("Phaser: The exam are going to start. " +
                "The students are ready.");
        System.out.printf("Phaser: We have %d students.\n",
                this.getRegisteredParties());
        return false;
    }

    private boolean finishFirstExercise() {
        //打印所有学生均完成第一题的信息
        System.out.println("Phaser: All the students have finished " +
                "the first exercise.");
        System.out.println("Phaser: It's time for the second one.");
        return false;
    }

    private boolean finishSecondExercise() {
        //打印所有学生均完成第二题的信息
        System.out.println("Phaser: All the students have finished " +
                "the second exercise.");
        System.out.println("Phaser: It's time for the third one.");
        return false;
    }

    private boolean finishExam() {
        //打印所有学生均完成考试的信息
        System.out.println("Phaser: All the students have finished " +
                "the exam.");
        System.out.println("Phaser: Thank for your time.");
        return true;
    }

}
