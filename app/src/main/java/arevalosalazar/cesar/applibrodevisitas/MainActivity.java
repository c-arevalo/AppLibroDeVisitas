package arevalosalazar.cesar.applibrodevisitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private final static String NOMBRE_FICHERO = "visitantes.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText et = null;
        et.setOnKeyListener((new android.view.View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, android.view.KeyEvent event){
                if ((event.getAction()== KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)){
                    onNuevoNombre();
                    return true;
                }
                return false;
            }
        }));
        actualizarVisitantes();
    }

    @Override
    protected void onNuevoNombre(){
        EditText et;
        et = (EditText) findViewById(R.id.txtNombre);
        String nNombre;
        nNombre = et.getText().toString();
        if (nNombre.trim().equals("")){
            muestraMensaje(R.string.error);
            return;}
        try{
            FileOutputStream fos;
            fos = openFileOutput(NOMBRE_FICHERO, Context.MODE_PRIVATE | Context.MODE_APPEND);
            java.io.OutputStreamWriter osw;
            osw = new OutputStreamWriter(fos);
            osw.write(nNombre + "\n");
            osw.close();
            muestraMensaje(R.string.bienvenido);
            actualizarVisitantes();
        }catch (Exception e){
            muestraMensaje(R.string.errorR);
        }
        InputMethodManager imm;
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(),0);
        et.requestFocus();
        et.setText("");
    }
}