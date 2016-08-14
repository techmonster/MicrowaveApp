package edu.dtcc.nhollowa.microwaveapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String time = "";
    boolean started = false;
    int totalTime = 0;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // create button objects and assign common listener
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(myButtonListener);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(myButtonListener);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(myButtonListener);

        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(myButtonListener);

        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(myButtonListener);

        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(myButtonListener);

        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(myButtonListener);

        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(myButtonListener);

        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(myButtonListener);

        Button button0 = (Button) findViewById(R.id.button0);
        button0.setOnClickListener(myButtonListener);

        Button buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStart.setOnClickListener(myButtonListener);

        Button buttonStop = (Button) findViewById(R.id.buttonStop);
        buttonStop.setOnClickListener(myButtonListener);
    }

    private View.OnClickListener myButtonListener;
    {
        myButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                Button b = (Button) v;
                final TextView tv = (TextView) findViewById(R.id.textView);
                String s = b.getText().toString();




                // Simulate processing based on the button pressed
                switch (s) {
                    case "1":
                        s = "1";
                        break;
                    case "2":
                        s = "2";
                        break;
                    case "3":
                        s = "3";
                        break;
                    case "4":
                        s = "4";
                        break;
                    case "5":
                        s = "5";
                        break;
                    case "6":
                        s = "6";
                        break;
                    case "7":
                        s = "7";
                        break;
                    case "8":
                        s = "8";
                        break;
                    case "9":
                        s = "9";
                        break;
                    case "0":
                        s = "0";
                        break;
                    case "Start":
                        if(started){
                            s = "Invalid operation";
                            // Pop a toast message
                            Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                        }
                        else {
                            s = "Starting";
                            if(totalTime == 0){

                            //get the time in seconds
                             totalTime = countDown();
                            //convert the seconds to milliseconds
                             totalTime = totalTime * 1000;
                            }
                            countDownTimer = new CountDownTimer(totalTime, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    long seconds = millisUntilFinished / 1000;
                                    //convert the seconds to minutes and seconds
                                    //String time = seconds / 60 + ":" + seconds % 60;
                                    long minutes = (seconds / 60);
                                    long secs = (seconds % 60);
                                     time = String.format("%d:%02d", minutes, secs);
                                    //set display
                                    tv.setText(time);

                                    //update totalTime in case of restart
                                    totalTime = (((int)seconds)*1000);
                                }

                                @Override
                                public void onFinish() {

                                    tv.setText(R.string.doneMessage);
                                    started = false;
                                    time = "";
                                    totalTime = 0;
                                }
                            }.start();

                            countDownTimer.start();
                            started = true;
                        }
                        break;
                    case "Stop":

                        if(!started){
                            time = "";

                            tv.setText(time);
                            s = "Clear!";
                            totalTime = 0;
                        } else {
                            s = "Done!";
                            started = false;
                        }
                        try {
                            countDownTimer.cancel();
                            started = false;
                        } catch (NullPointerException c){
                            System.out.println(c.getMessage());
                        }

                        break;
                    default:
                        s = "unhandled button pressed";
                }

                // Write a message string to the text view
                if (!s.equals("Starting") && !s.equals("Done!") && !s.equals("Clear!")&& !s.equals("Invalid operation"))
                    tv.setText(time += s);

                // Pop a toast message
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();


            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    int countDown(){
        //if the time is less than 4 numbers add 0's to the front
        if(time.length()<4){

            for(int i = time.length(); i < 4; i++){
                time = "0" + time;
            }
        }
       String cookTime = time.substring((time.length()-4));
        String seconds = cookTime.substring((cookTime.length()-2));
        String minutes = cookTime.substring((cookTime.length()-4),(cookTime.length()-2));

        System.out.println("Minutes are: "+minutes+ "\nSeconds are: "+seconds);
        int totalCookTime = Integer.parseInt(cookTime);
        int secs = Integer.parseInt(seconds);
        int mins = Integer.parseInt(minutes);
        if ( totalCookTime > 0){
            //limit seconds to 59 seconds
            if(secs >= 60){
                secs =  secs - 60;
                mins++;
            }
            //limit time to 20 minutes
            if(mins >= 20){
                mins = 20;
                secs = 0;
            }
            //multiply the minutes by 60 seconds to get the time in seconds
            totalCookTime = (mins*60) + secs;


        } else{
            String message = "Invalid time";
            // Pop a toast message
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        }
        return totalCookTime;
    }


}



