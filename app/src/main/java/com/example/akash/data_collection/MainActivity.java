package com.example.akash.data_collection;

import android.os.Bundle;
import android.widget.TextView;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.view.View;
import android.widget.Button;


import java.io.FileWriter;
import java.io.IOException;


import static java.lang.Math.sqrt;
public class MainActivity extends Activity implements SensorEventListener,
        View.OnClickListener {
    private SensorManager sensorManager;
    private Button stand, sit,walk,stop,start,C, collapse;
    private boolean standed = false;
    private boolean sitted = false;
    private boolean walked = false;
    private boolean Clp= false;
    float a=0.1f;

    private FileWriter awriter , gwriter;

    {
        try {
            awriter = new FileWriter("/mnt/sdcard/my_acc_data.csv",true);
            gwriter = new FileWriter("/mnt/sdcard/my_gyro_data.csv",true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        stand =  findViewById(R.id.button4);
        sit =  findViewById(R.id.button5);
        start=findViewById(R.id.start);
        walk = findViewById(R.id.button6);
        C=findViewById(R.id.button2);
        stop = findViewById(R.id.button);
        stand.setOnClickListener(this);
        sit.setOnClickListener(this);
        walk.setOnClickListener(this);
        C.setOnClickListener(this);
        stop.setOnClickListener(this);
        //collapse.setOnClickListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    float lowPass(float current, float last)
    {
        return last * (1.0f - a) + current * a;
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        final int type = event.sensor.getType();
        long timestamp = System.currentTimeMillis()/10;
        if (type == Sensor.TYPE_ACCELEROMETER) {
            if (standed) {
                double x = event.values[0];
                double y = event.values[1];
                double z = event.values[2];
                double a=sqrt((x*x)+(y*y)+(z*z));
                String s = "standing";

                float mLowPassX =-1f, mLowPassY=-1f, mLowPassZ=-1f;


                if(mLowPassX == -1f){
                    mLowPassX = (float) x;
                }
                if(mLowPassY == -1f){
                    mLowPassY = (float) y;
                }
                if(mLowPassZ == -1f){
                    mLowPassZ = (float) z;
                }

                mLowPassX = lowPass((float) x, mLowPassX);
                mLowPassY = lowPass((float) y, mLowPassY);
                mLowPassZ = lowPass((float) z, mLowPassZ);
                try {
                    awriter.write("acc,"+timestamp + "," + mLowPassX + "," + mLowPassY + "," + mLowPassZ + ","+a+"," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sitted) {
                double x = event.values[0];
                double y = event.values[1];
                double z = event.values[2];
                double a=sqrt((x*x)+(y*y)+(z*z));
                String s = "sitting";
                float mLowPassX =-1f, mLowPassY=-1f, mLowPassZ=-1f;


                if(mLowPassX == -1f){
                    mLowPassX = (float) x;
                }
                if(mLowPassY == -1f){
                    mLowPassY = (float) y;
                }
                if(mLowPassZ == -1f){
                    mLowPassZ = (float) z;
                }

                mLowPassX = lowPass((float) x, mLowPassX);
                mLowPassY = lowPass((float) y, mLowPassY);
                mLowPassZ = lowPass((float) z, mLowPassZ);
                try {
                    awriter.write("acc,"+timestamp + "," + mLowPassX + "," + mLowPassY + "," + mLowPassZ + ","+a+"," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (walked) {
                double x = event.values[0];
                double y = event.values[1];
                double z = event.values[2];
                double a=sqrt((x*x)+(y*y)+(z*z));
                String s = "walking";
                float mLowPassX =-1f, mLowPassY=-1f, mLowPassZ=-1f;


                if(mLowPassX == -1f){
                    mLowPassX = (float) x;
                }
                if(mLowPassY == -1f){
                    mLowPassY = (float) y;
                }
                if(mLowPassZ == -1f){
                    mLowPassZ = (float) z;
                }

                mLowPassX = lowPass((float) x, mLowPassX);
                mLowPassY = lowPass((float) y, mLowPassY);
                mLowPassZ = lowPass((float) z, mLowPassZ);
                try {
                    awriter.write("acc,"+timestamp + "," + mLowPassX + "," + mLowPassY + "," + mLowPassZ + ","+a+"," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Clp) {
                double x = event.values[0];
                double y = event.values[1];
                double z = event.values[2];
                double a=sqrt((x*x)+(y*y)+(z*z));
                String s = "collapse";
                float mLowPassX =-1f, mLowPassY=-1f, mLowPassZ=-1f;


                if(mLowPassX == -1f){
                    mLowPassX = (float) x;
                }
                if(mLowPassY == -1f){
                    mLowPassY = (float) y;
                }
                if(mLowPassZ == -1f){
                    mLowPassZ = (float) z;
                }

                mLowPassX = lowPass((float) x, mLowPassX);
                mLowPassY = lowPass((float) y, mLowPassY);
                mLowPassZ = lowPass((float) z, mLowPassZ);
                try {
                    awriter.write("acc,"+timestamp + "," + mLowPassX + "," + mLowPassY + "," + mLowPassZ + ","+a+"," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                    //System.out.println("record added");
                    //System.out.println("record deleted");
                }
            }
        }


        if (type == Sensor.TYPE_GYROSCOPE) {
            if (standed) {
                double x1 = event.values[0];
                double y1 = event.values[1];
                double z1 = event.values[2];
                String s = "standing";
                try {
                    gwriter.write("gyro,"+timestamp + "," + x1 + "," + y1 + "," + z1 + "," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sitted) {
                double x1 = event.values[0];
                double y1 = event.values[1];
                double z1 = event.values[2];
                String s = "sitting";
                //long timestamp = System.currentTimeMillis();
                // AccelData data = new AccelData(timestamp, x, y, z,s);
                // sensorData.add(data);
                try {
                    // writer.write("time:"+timestamp+"X:"+x+"Y:"+y+"Z:"+z+"label:"+s+"\n");
                    gwriter.write("gyro,"+timestamp + "," + x1 + "," + y1 + "," + z1 + "," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (walked) {
                double x1 = event.values[0];
                double y1 = event.values[1];
                double z1 = event.values[2];
                String s = "walking";
                try {
                    // writer.write(data+"\n");
                    //writer.write("time:"+timestamp+"X:"+x+"Y:"+y+"Z:"+z+"label:"+s+"\n");
                    gwriter.write("gyro,"+timestamp + "," + x1 + "," + y1 + "," + z1 + "," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (Clp) {
                double x1 = event.values[0];
                double y1 = event.values[1];
                double z1 = event.values[2];
                String s = "collapse";
                try {
                    gwriter.write("gyro," + timestamp + "," + x1 + "," + y1 + "," + z1 + "," + s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
    protected void onResume() {
        super.onResume();
        // writer = new FileWriter("myfile.txt",true);
    }

    protected void onPause() {
        super.onPause();

        if(awriter != null) {
            try {
                awriter.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        if(gwriter != null) {
            try {
                gwriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button4:
                stand.setEnabled(false);
                sit.setEnabled(true);
                walk.setEnabled(true);
                C.setEnabled(true);

                standed = true;
                sitted=false;
                walked=false;
                Clp=false;
                Sensor accel = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                Sensor gyro= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(this, accel,
                        SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.button5:
                stand.setEnabled(true);
                sit.setEnabled(false);
                walk.setEnabled(true);
                C.setEnabled(true);


                standed = false;
                sitted=true;
                walked=false;
                Clp=false;
                accel = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                gyro= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this, accel,
                        SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.button6:
                stand.setEnabled(true);
                sit.setEnabled(true);
                walk.setEnabled(false);
                C.setEnabled(true);
                // collapse=true;
                standed =false;
                sitted=false;
                walked=true;
                Clp=false;
                accel = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(this, accel,
                        SensorManager.SENSOR_DELAY_NORMAL);
                gyro= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.button2:
                stand.setEnabled(true);
                sit.setEnabled(true);
                walk.setEnabled(true);
                C.setEnabled(false);


                standed = false;
                sitted=false;
                walked=false;
                Clp=true;
                accel = sensorManager
                        .getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                sensorManager.registerListener(this, accel,
                        SensorManager.SENSOR_DELAY_NORMAL);
                gyro= sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                sensorManager.registerListener(this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                break;

            case R.id.button:
                stand.setEnabled(false);
                sit.setEnabled(false);
                walk.setEnabled(false);
                C.setEnabled(false);

                standed = false;
                sitted=false;
                walked=false;
                Clp=false;
                sensorManager.unregisterListener(this);
            default:
                break;
        }

    }
}


