package hise.hznu.istudy.util.clip;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.v4.app.Fragment;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.utils.StorageUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import hise.hznu.istudy.app.AppConfig;
import hise.hznu.istudy.util.IOUtil;

public class PhotoHelper {
    public static final int REQUEST_SELECT_PHOTO = 1988;
    public static final int REQUEST_CLIP_PHOTO = 1989;
    public static final int REQUEST_TAKE_PHOTO = 1990;
    private static final String JPG = "jpg";
    private static final String PNG = "png";
    public static DisplayImageOptions defaultBigLoadingOptions = (new Builder()).considerExifParams(true).resetViewBeforeLoading(true).cacheInMemory(true).cacheOnDisk(true).build();
    private static final long MAX_AVATAR_SIZE_IN_BYTES = 102400L;
    private static final int MAX_AVATAR_SIZE_IN_WIDTH = 360;
    private static final int MAX_AVATAR_SIZE_IN_HEIGHT = 360;
    private static File takePhotoUri;

    public PhotoHelper() {
    }

    public static void startClipPhoto(Fragment fragment, File file, String backText) {
        Intent it = new Intent(fragment.getActivity(), ClipActivity.class);
        it.setData(Uri.fromFile(file));
        it.putExtra("__extra_left_photo_text__", backText);
        fragment.startActivityForResult(it, 1989);
    }

    public static File parseClipResult(int requestCode, int resultCode, Intent data) {
        if(1989 != requestCode) {
            return null;
        } else if(resultCode != -1) {
            return null;
        } else if(data == null) {
            return null;
        } else {
            Uri uri = data.getData();
            if(uri == null) {
                return null;
            } else {
                String path = uri.getPath();
                return new File(path);
            }
        }
    }

    public static File parseTakePhotoResult(int requestCode, int resultCode, Intent data) {
        return 1990 != requestCode?null:(resultCode != -1?null:takePhotoUri);
    }

    public static File persist(Bitmap bm) throws Exception {
        if(bm == null) {
            return null;
        } else if(bm.getWidth() != 0 && bm.getHeight() != 0) {
            FileOutputStream fos = null;
            File ret = null;

            try {
                ret = genOutFile("png");
                fos = new FileOutputStream(ret);
                bm.compress(CompressFormat.PNG, 100, fos);
            } catch (Exception var7) {
                var7.printStackTrace();
            } finally {
                if(fos != null) {
                    fos.close();
                }

            }

            if(ret != null) {
                if(!ret.exists()) {
                    return null;
                }

                if(ret.length() == 0L) {
                    return null;
                }
            }

            return ret;
        } else {
            return null;
        }
    }

    private static File genOutFile(String suffix) {
        String name = System.currentTimeMillis() + "." + suffix;
        File dir = StorageUtils.getCacheDirectory(AppConfig.getContext());
        dir.mkdirs();
        return new File(dir, name);
    }

    public static File openCamera(Fragment ctx) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        File retFile = genOutFile("jpg");
        Uri uri = Uri.fromFile(retFile);
        intent.putExtra("output", uri);
        ctx.startActivityForResult(intent, 1990);
        takePhotoUri = retFile;
        return retFile;
    }

    public static byte[] compressAvatar(File file) throws Exception {
        if(file.length() > 102400L) {
            Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath());
            if(bm == null) {
                throw new RuntimeException();
            } else {
                Bitmap buffer = Bitmap.createScaledBitmap(bm, 360, 360, true);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                buffer.compress(CompressFormat.JPEG, 100, out);
                return out.toByteArray();
            }
        } else {
            return IOUtil.readBytes(file);
        }
    }
}

