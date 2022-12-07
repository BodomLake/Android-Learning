package com.bodomlake.core.provider;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import com.bodomlake.core.webview.WebViewActivity;
import com.bodomlake.helloworld.R;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ContentProviderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cp_layout);
        findViewById(R.id.btn_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContacts();
            }
        });
        findViewById(R.id.btn_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });
        findViewById(R.id.push_info_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushInfo();
            }
        });
        findViewById(R.id.mac_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMacAddress();
            }
        });
        findViewById(R.id.wlan_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWlanMACaddress();
            }
        });
        findViewById(R.id.wlan_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getGeoLocation();
            }
        });
        findViewById(R.id.camera_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });
        findViewById(R.id.audio_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecord();
            }
        });
        findViewById(R.id.screen_shot_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenShot();
            }
        });
        //从6.0系统开始,需要动态获取权限
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        // 试图拿到 联系人 读取权限
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }
    }

    private void takeScreenShot() {

    }

    private void startRecord() {
    }

    private void openCamera() {
    }

    private void getContacts() {
        //①查询raw_contacts表获得联系人的id
        ContentResolver resolver = getContentResolver();
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        //查询联系人数据
        Cursor cursor = resolver.query(uri, null, null, null, null);
        while (cursor.moveToNext()) {
            //获取联系人姓名,手机号码
            String cName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String cNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.e("bodomlake", "姓名:" + cName);
            Log.e("bodomlake", "号码:" + cNum);
            Log.e("bodomlake", "======================");
        }
        cursor.close();
    }

    private void addContact() {
        try {
            //使用事务添加联系人
            Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
            Uri dataUri = Uri.parse("content://com.android.contacts/data");

            ContentResolver resolver = getContentResolver();
            ArrayList<ContentProviderOperation> operations = new ArrayList<ContentProviderOperation>();

            //为了便于Android中进行批量数据库操作时效率更高，Android中推荐使用ContentProviderOperation
            ContentProviderOperation op1 = ContentProviderOperation.newInsert(uri)
                    .withValue("account_name", null)
                    .build();
            operations.add(op1);

            //依次是姓名，号码，邮编
            ContentProviderOperation op2 = ContentProviderOperation.newInsert(dataUri)
                    .withValueBackReference("raw_contact_id", 0)
                    .withValue("mimetype", "vnd.android.cursor.item/name")
                    .withValue("data2", "张三")
                    .build();
            operations.add(op2);

            ContentProviderOperation op3 = ContentProviderOperation.newInsert(dataUri)
                    .withValueBackReference("raw_contact_id", 0)
                    .withValue("mimetype", "vnd.android.cursor.item/phone_v2")
                    .withValue("data1", "13988776771")
                    .withValue("data2", "2")
                    .build();
            operations.add(op3);

            ContentProviderOperation op4 = ContentProviderOperation.newInsert(dataUri)
                    .withValueBackReference("raw_contact_id", 0)
                    .withValue("mimetype", "vnd.android.cursor.item/email_v2")
                    .withValue("data1", "792294021@qq.com")
                    .withValue("data2", "2")
                    .build();
            operations.add(op4);
            //将上述内容添加到手机联系人中~
            resolver.applyBatch("com.android.contacts", operations);
            Toast.makeText(getApplicationContext(), "添加成功", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("bodomlake", e.getMessage());
        }
    }

    private void pushInfo() {
        Log.e("bodomlake", "pushInfo");

        // 获取系统服务（消息推送管理器）
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
/*        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
            manager = getSystemService(NotificationManager.class);
        }*/
        Intent intent = new Intent(this, WebViewActivity.class);
        //  flags: FLAG_ONE_SHOT, FLAG_NO_CREATE, FLAG_CANCEL_CURRENT, FLAG_UPDATE_CURRENT
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        // @SuppressLint("ResourceType")
        // PendingIntent pendingIntent2 = new NavDeepLinkBuilder(getApplicationContext()).setGraph(R.id.nav_graph).setDestination(R.id.FirstFragment).createPendingIntent();
        String channelId = UUID.randomUUID().toString();
        // 设置通知，为了更好的兼容性，可以调用support-v4的NotificationCompat来设置
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setContentTitle("PendingIntent-Title")
                .setContentIntent(pendingIntent)
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setContentText("点击跳转到WebView页面")
                .setWhen(System.currentTimeMillis())
                //设置闪烁灯：3个参数：颜色，灯亮时长，灯暗时长
                .setLights(Color.YELLOW, 1000, 1000)
                .setSmallIcon(R.drawable.bunny_green)
                // 设置震动：参数一个long[]{}数组，里面可以依次设置：静止时长，震动时长，静止时长，震动时长....
                .setVibrate(new long[]{200, 1000, 1000, 1000});

        // builder.setAutoCancel(true);
        // NotificationChannel是Android Oreo新增的通知渠道，其允许您为要显示的每种通知类型创建用户可自定义的渠道
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, "my_random_uuid_channel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.enableLights(true);
            channel.setLightColor(Color.green(255));
            channel.setShowBadge(true);
            manager.createNotificationChannel(channel);
            // 消息构造器的 消息渠道id 要和 消息渠道的构造函数设置的 消息渠道id 一致
            // builder.setChannelId(channelId);
        }
        // .setLargeIcon(BitmapFactory.decodeResource(getResource(),R.drawable.large_icon))
        Notification notification = builder.build();
        int messageId = (int) (Math.random() * 1000);
        manager.notify(messageId, notification);
        // manager.cancel(messageId);
    }

    public String getMacAddress() {
        try {
            List<NetworkInterface> networkInterfaceList = Collections.list(NetworkInterface.getNetworkInterfaces());
            // List<String> list = new Vector<>();
            String stringMac = "";

            for (NetworkInterface networkInterface : networkInterfaceList) {
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) ;
                {
                    for (int i = 0; i < networkInterface.getHardwareAddress().length; i++) {
                        String stringMacByte = Integer.toHexString(networkInterface.getHardwareAddress()[i] & 0xFF);
                        if (stringMacByte.length() == 1) {
                            stringMacByte = "0" + stringMacByte;
                        }
                        stringMac = stringMac + stringMacByte.toUpperCase() + ":";
                    }
                    break;
                }
            }
            if (stringMac.endsWith(":")) {
                stringMac = stringMac.substring(0, stringMac.lastIndexOf(":"));
            }
            Log.e("bodomlake", stringMac);
            Toast.makeText(getApplicationContext(), stringMac, Toast.LENGTH_LONG).show();
            return stringMac;
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "0";
    }

    public String getWlanMACaddress() {
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String macAddress = wifiInfo == null ? null : wifiInfo.getMacAddress();
        Toast.makeText(getApplicationContext(), macAddress, Toast.LENGTH_LONG).show();
        return macAddress;
    }

    private Location getGeoLocation() {
        LocationManager locManger = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean fine_locate_flag = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        boolean coarse_locate_flag = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED;
        // 如果粗细地位都没有开启
        if (fine_locate_flag && coarse_locate_flag) {
            String[] permissionToApply = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(this, permissionToApply, 3);
        }
        // 首先请求 GPS
        Location loc = locManger.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (loc == null) {
            // 换成网络请求，获取地理位置
            loc = locManger.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        return loc;
    }

    public void LocationMethod() {// Location常用方法简介

        Location loc = getGeoLocation();

        // loc.distanceTo(Location dest);float
        // loc.getAltitude();double 海拔高度
        // loc.getLatitude();double
        // loc.getLongitude();double
        // loc.getSpeed();float

    }

    // requestPermissions 完成之后的回调
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("bodomlake", String.valueOf(requestCode));
        boolean flag = (grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED);
        switch (requestCode) {
            case 0:
                if (flag) {
                    Toast.makeText(ContentProviderActivity.this, "联系人权限授权成功", Toast.LENGTH_SHORT).show();
                    // 试图拿到写权限
                    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS}, 1);
                    }
                }
                break;
            case 1:
                if (flag) {
                    Toast.makeText(ContentProviderActivity.this, "写入联系人权限授权成功", Toast.LENGTH_SHORT).show();
                }
                break;
            // 请求 地理位置
            case 2:
                if (flag) {
                    Toast.makeText(ContentProviderActivity.this, "获取地理位置权限已经开通", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }


}
