package com.who.onecupafterwork.signin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.who.onecupafterwork.adapter.MySpinnerAdapter;
import com.who.onecupafterwork.data.Login;
import com.who.onecupafterwork.data.Person;
import com.who.onecupafterwork.data.ResultData;
import com.who.onecupafterwork.network.NetworkModel;
import com.who.onecupafterwork.network.NetworkModel.OnNetworkResultListener;
import com.who.onecupafterwork.preferences.PropertyManager;
import com.who.onecupafterwork.show.QustomDialogBuilder;

public class SignInActivity extends ActionBarActivity {

	TextView stipulationView;
	Spinner phoneNum1Spinner;
	EditText idView, pswdView, confirmPswdView, phoneNum2View, phoneNum3View, facebookView, nameView, yearView, monthView, dayView, emailView,
			domainView;
	RadioGroup genderGroup;
	CheckBox agreeCheck;
	ImageView profileImage, imageViewAddPhoto;
	ActionBar actionBar;
	
	boolean idCheck = false;
	boolean phonNumCheck = false;
	String id, pswd, confirmPswd, phoneNumber1, phoneNumber2, phoneNumber3, fullPhoneNum, facebookAddress, name, year, month, day, fullEmail;
	String gender = "M";
	String[] phone1 = { "010", "011", "016", "019" };
	
	MySpinnerAdapter phoneAdapter;

	private static String TEMP_PHOTO_FILE = System.currentTimeMillis() + ".jpg";
	private static String TEMP_CAMERA_FILE = System.currentTimeMillis() + ".jpg";

	public static final int REQUEST_CODE_CAMERA = 0;
	public static final int REQUEST_CODE_GALLERY = 1;
	public static final int REQUEST_CROP = 2;
	
	File mSavedFile;
	File whoFolder = new File(Environment.getExternalStorageDirectory() + "/Who");

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sign_in_activity);

		actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setDisplayShowHomeEnabled(false);
		actionBar.setDisplayShowTitleEnabled(false);

		genderGroup = (RadioGroup) findViewById(R.id.genderGroup);
		genderGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.male:
				default:
					gender = "M";
					break;
				case R.id.female:
					gender = "F";
					break;
				}
			}
		});

		idView = (EditText) findViewById(R.id.signIdView);
		pswdView = (EditText) findViewById(R.id.signPswdView);
		confirmPswdView = (EditText) findViewById(R.id.confirmPswdView);
		nameView = (EditText) findViewById(R.id.nameView);
		yearView = (EditText) findViewById(R.id.yearView);
		// monthView = (EditText) findViewById(R.id.monthView);
		// dayView = (EditText) findViewById(R.id.dayView);
		phoneNum1Spinner = (Spinner) findViewById(R.id.phoneNum1Spinner);
		phoneNum2View = (EditText) findViewById(R.id.phoneNum2View);
		phoneNum3View = (EditText) findViewById(R.id.phoneNum3View);
		agreeCheck = (CheckBox) findViewById(R.id.agreeCheck);
		stipulationView = (TextView) findViewById(R.id.stipulationView);
		profileImage = (ImageView) findViewById(R.id.profileImageView);
		imageViewAddPhoto = (ImageView) findViewById(R.id.imageViewAddPhoto);
		emailView = (EditText) findViewById(R.id.signInEmailView);
		domainView = (EditText) findViewById(R.id.signInEmailDomainView);

		phoneAdapter = new MySpinnerAdapter(this, phone1);
		phoneNum1Spinner.setAdapter(phoneAdapter);
		phoneNum1Spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> adapter, View v, int position, long arg3) {
				phoneNumber1 = (String) phoneNum1Spinner.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		Button btnCheckPhone = (Button) findViewById(R.id.btnCheckId);
		btnCheckPhone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				id = idView.getText().toString();

				if (id != null && !id.equals("")) {
					NetworkModel.getInstance().postCheckId(id, SignInActivity.this, new OnNetworkResultListener<ResultData>() {

						@Override
						public void onResult(ResultData result) {
							QustomDialogBuilder builder = new QustomDialogBuilder(SignInActivity.this);
							builder.setTitle("Check Duplicated value");
							if (result.result.equals("success")) {
								builder.setMessage("사용가능한 아이디입니다");
								builder.setTitleColor("#8C76E7");
								builder.setDividerColor("#8C76E7");
								builder.setPositiveButton("확인", new OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();
										idCheck = true;
									}
								});
								builder.create().show();
							} else {
								builder.setMessage("이미 사용중인 아이디 입니다.");
								idView.setText("");
								builder.setPositiveButton("확인", new OnClickListener() {
									@Override
									public void onClick(DialogInterface dialog, int which) {
										dialog.dismiss();
										idCheck = false;
									}
								});
								builder.create().show();
							}
						}
					});
				} else {
					Toast.makeText(SignInActivity.this, "please give a id.", Toast.LENGTH_SHORT).show();
				}
			}
		});

		Button btn = (Button) findViewById(R.id.btnRegister);
		btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				pswd = pswdView.getText().toString();

				confirmPswd = confirmPswdView.getText().toString();
				name = nameView.getText().toString();
				phoneNumber2 = phoneNum2View.getText().toString();
				phoneNumber3 = phoneNum3View.getText().toString();
				fullPhoneNum = phoneNumber1 + phoneNumber2 + phoneNumber3;
				year = yearView.getText().toString();
				// month = monthView.getText().toString();
				// day = dayView.getText().toString();
				fullEmail = emailView.getText().toString() + "@" + domainView.getText().toString(); // db로 보내기

				if (!filePath.equals("notsetting") && filePath != null) {
					if (name != null && !name.equals("")) {
						if (idCheck) {
							if (!pswd.equals("") && pswd != null && pswd.equals(confirmPswd)) {
								if (year != null && !year.equals("") && Integer.parseInt(year) >= 1950 && Integer.parseInt(year) <= 1995) {
									// if (year != null && !year.equals("") && month != null && !month.equals("") && day != null && !day.equals("")
									// && Integer.parseInt(year) >= 1950 && Integer.parseInt(year) <= 1995 && Integer.parseInt(month) >= 1
									// && Integer.parseInt(month) <= 12 && Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 31) {

									/* 생년월일 입력 시 월, 날짜 일의 단위로 입력 했을 때의 처리 */
									// if (Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 9) {
									// if (month.charAt(0) != '0') {
									// month = "0" + month;
									// }
									// }
									//
									// if (Integer.parseInt(day) >= 1 && Integer.parseInt(day) <= 9) {
									// if (day.charAt(0) != '0') {
									// day = "0" + day;
									// }
									// }
									/* ---------- */

									if (!emailView.getText().toString().equals("") && !domainView.getText().toString().equals("")) {
										// if (phonNumCheck) {
										if (agreeCheck.isChecked()) {
											Person person = new Person();
											person.uname = name;
											person.uage = year + month + day;
											Log.i("TOP : ", person.uage);
											person.uid = id;
											person.upass = pswd;
											person.uphon = fullPhoneNum;
											person.ugen = gender;
											person.umail = fullEmail;
											person.uphoto = new File(filePath);

											NetworkModel.getInstance().postSignIn(person, SignInActivity.this,
													new OnNetworkResultListener<ResultData>() {

														@Override
														public void onResult(ResultData result) {
															if (result.result.equals("success")) {
																Log.i("Success Sign in", result.result);
																Login login = new Login(id, pswd);
																NetworkModel.getInstance().postLogin(login, SignInActivity.this,
																		new OnNetworkResultListener<ResultData>() {

																			@Override
																			public void onResult(ResultData result) {
																				if (result.result.equals("success")) {

																					String userStatus = result.uprocess;
																					System.out.println("User Status1 : " + userStatus);
																					PropertyManager.getInstance().setUserStatus(userStatus);

																					PropertyManager.getInstance().setUserId(id);
																					PropertyManager.getInstance().setUserPassword(pswd);

																					// Intent i = new Intent(SignInActivity.this,
																					// SelectCompanyActivity.class);
																					Intent i = new Intent(SignInActivity.this, ProfileActivity.class);
																					startActivity(i);
																					finish();
																					Toast.makeText(SignInActivity.this, "로그인 성공", Toast.LENGTH_SHORT)
																							.show();
																				} else {
																					Toast.makeText(SignInActivity.this, "회원가입 직후 로그인 실패",
																							Toast.LENGTH_SHORT).show();
																				}
																			}
																		});
															} else {
																Toast.makeText(SignInActivity.this, "회원 가입 연결 실패", Toast.LENGTH_SHORT).show();
															}
														}
													});
										} else {
											Toast.makeText(SignInActivity.this, "약관에 동의하세요.", Toast.LENGTH_SHORT).show();
										}
										// } else {
										// Toast.makeText(SignInActivity.this, "휴대폰 인증을 거치셔야 합니다.", Toast.LENGTH_SHORT).show();
										// }
									} else {
										Toast.makeText(SignInActivity.this, "이메일 올바로 입력하세요.", Toast.LENGTH_SHORT).show();
									}
								} else {
									Toast.makeText(SignInActivity.this, "1996년 이전 출생자만 가입 가능합니다.", Toast.LENGTH_SHORT).show();
								}
							} else {
								Toast.makeText(SignInActivity.this, "Password not matching", Toast.LENGTH_SHORT).show();
							}
						} else {
							Toast.makeText(SignInActivity.this, "id 중복체크 하세요.", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(SignInActivity.this, "이름 입력하세요.", Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(SignInActivity.this, "프로필 사진을 등록해주세요.", Toast.LENGTH_SHORT).show();
				}

			}
		});

		final CharSequence[] list = { "사진 찍기", "갤러리에서 불러오기" };

		System.out.println("check");
		// profileImage.setScaleType(ScaleType.CENTER_CROP);
		profileImage.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
				TEMP_PHOTO_FILE = System.currentTimeMillis() + ".jpg";
				TEMP_CAMERA_FILE = System.currentTimeMillis() + ".jpg";

				builder.setItems(list, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						case REQUEST_CODE_CAMERA:
							Intent photoPickerIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							Uri fileUri = getTempCameraUri();
							photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
							startActivityForResult(photoPickerIntent, REQUEST_CODE_CAMERA);

							break;
						case REQUEST_CODE_GALLERY:
							photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
							photoPickerIntent.setType("image/*");
							startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);

							break;
						}
						dialog.dismiss();
					}
				});
				builder.create().show();
			}
		});

		if (savedInstanceState != null) {
			mSavedFile = new File(savedInstanceState.getString("filename"));
		}

		stipulationView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getSupportFragmentManager();
				StipulationCustomDialog dialog = new StipulationCustomDialog();
				FragmentTransaction ft = fm.beginTransaction();
				ft.addToBackStack(null);
				dialog.show(ft, "dialog");
			}
		});

		agreeCheck.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				FragmentManager fm = getSupportFragmentManager();
				StipulationCustomDialog dialog = new StipulationCustomDialog();
				FragmentTransaction ft = fm.beginTransaction();
				ft.addToBackStack(null);
				dialog.show(ft, "dialog");
			}
		});
	}

	private void cropImage(Uri uri) {
		if (uri != null) {
			Intent photoPickerIntent = new Intent("com.android.camera.action.CROP", uri);
			photoPickerIntent.putExtra("outputX", 1500);
			photoPickerIntent.putExtra("outputY", 1500);
			photoPickerIntent.putExtra("aspectX", 1);
			photoPickerIntent.putExtra("aspectY", 1);
			photoPickerIntent.putExtra(MediaStore.EXTRA_OUTPUT, getTempUri());
			photoPickerIntent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

			startActivityForResult(photoPickerIntent, REQUEST_CROP);
			Log.i("check gallery", "check2" + REQUEST_CROP);
		}
	}

	Bitmap personBitmapImage;
	String filePath = "notsetting";

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE_CAMERA && resultCode == Activity.RESULT_OK) {
			try {
				String imagePath = whoFolder.getPath() + "/" + TEMP_CAMERA_FILE;
				String url = MediaStore.Images.Media.insertImage(getContentResolver(), imagePath, "camera image", "original image");
				Uri photouri = Uri.parse(url);
				ContentValues values = new ContentValues();
				values.put(MediaStore.Images.Media.ORIENTATION, 90);
//				values.put(MediaStore.Images.Media.ORIENTATION,0); //Vega
				getContentResolver().update(photouri, values, null, null);
				cropImage(photouri);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

		if (requestCode == REQUEST_CODE_GALLERY && resultCode == Activity.RESULT_OK) {
			Log.i("check gallery", "check1");
			Uri uri = data.getData();
			cropImage(uri);
		}

		if (requestCode == REQUEST_CROP&&resultCode==Activity.RESULT_OK) {
			Log.i("check gallery", "check3");
			filePath = whoFolder.getPath() + "/" + TEMP_PHOTO_FILE;
			Log.i("check file Path", filePath);
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 4;
			Bitmap selectedImage = BitmapFactory.decodeFile(filePath, options);
//			Matrix matrix = new Matrix();
//			matrix.postRotate(180);
			// Bitmap lotateBitmap = Bitmap.createBitmap(selectedImage, 0, 0, selectedImage.getWidth(), selectedImage.getHeight(), matrix, true);
//			selectedImage = getCircleBitmap(selectedImage);
			// personBitmapImage = selectedImage;
			profileImage.setImageBitmap(selectedImage);
			imageViewAddPhoto.setVisibility(View.GONE);
		}

	}

	private Uri getTempUri() {
		return Uri.fromFile(getTempFile());
	}

	private File getTempFile() {

		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			if (!whoFolder.exists()) {
				whoFolder.mkdir();
			}
			System.out.println(whoFolder.getPath());
			mSavedFile = new File(whoFolder.getPath(), TEMP_PHOTO_FILE);
			try {
				mSavedFile.createNewFile();
			} catch (IOException e) {
				
			}
			return mSavedFile;
		} else {
			return null;
		}
	}

	private Uri getTempCameraUri() {
		return Uri.fromFile(getCameraFile());
	}

	private File getCameraFile() {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			if (!whoFolder.exists()) {
				whoFolder.mkdir();
			}
			System.out.println(whoFolder.getPath());
			File file = new File(whoFolder.getPath(), TEMP_CAMERA_FILE);
			try {
				file.createNewFile();
			} catch (IOException e) {
				
			}
			return file;
		} else {
			return null;
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		// outState.putString("filename", mSavedFile.getAbsolutePath());
	}

	/*public Bitmap getCircleBitmap(Bitmap bitmap) {

		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);

		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;

		final Paint paint = new Paint();

		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

		paint.setAntiAlias(true);

		canvas.drawARGB(0, 0, 0, 0);

		paint.setColor(color);

		int size = (bitmap.getWidth() / 2);

		canvas.drawCircle(size, size, size, paint);

		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));

		canvas.drawBitmap(bitmap, rect, rect, paint);

		return output;
	}*/

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Boolean check1 = intent.getBooleanExtra(StipulationCustomDialog.CHECK_STIPULATION, false);
		Boolean check2 = intent.getBooleanExtra(StipulationCustomDialog.CHECK_PRIVACY, false);
		if (check1 == true && check2 == true) {
			agreeCheck.setChecked(true);
		} else {
			agreeCheck.setChecked(false);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			Toast.makeText(SignInActivity.this, "로그인 페이지로 돌아갑니다.", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(SignInActivity.this, LoginActivity.class);
			startActivity(i);
			finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
