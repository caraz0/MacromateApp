package com.android.macromate.view.activities;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.android.macromate.R;
import com.android.macromate.core.mappers.IOpenFoodDtoToProductModelMapper;
import com.android.macromate.data.repositories.MockProductRepository;
import com.android.macromate.databinding.ActivityScanViewBinding;
import com.android.macromate.dtos.integration.OpenFoodFactsProductDTO;
import com.android.macromate.integration.service.IBarProductOpenFoodFactsService;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.google.zxing.Result;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class ScanActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityScanViewBinding binding;

    private CodeScanner mCodeScanner;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    @Inject
    IBarProductOpenFoodFactsService barProductOpenFoodFactsService;

    @Inject
    MockProductRepository mockProductRepository;

    @Inject
    ExecutorService executor;

    @Inject
    IOpenFoodDtoToProductModelMapper openFoodDtoToProductModelMapper;


    public ScanActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);             // Injection is done here

        setContentView(R.layout.activity_scan_view);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }

        CodeScannerView scannerView = findViewById(R.id.content_scan_view);

        mCodeScanner = new CodeScanner(this, scannerView);
        mCodeScanner.setDecodeCallback(this::decodedCallback);

        mCodeScanner.startPreview();
    }

    private void decodedCallback(Result result) {
        runOnUiThread(() -> {
                    // result.getText() es el código que posteriormente se le pasa a la API
                    Toast.makeText(ScanActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                    searchByBarcode(result.getText());

            /* TODO: Navegar a la página de detalles sobre el producto que se acaba de escanear

                Para ello, primero usamos onBackPressed(); para volver a la principal,
                e inmediatamente después hacemos el Intent a la actividad que nos muestra el
                resultado del scaneo. Hacemos esto porque si no apilaríamos todas las activities
                una encima de otra.

                Otra opción es crear un fragment y sustituir la vista del escáner por la del
                resultado, que tampoco estaría mal.

             */
                }
        );
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.content_scan_view);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCodeScanner.startPreview();
    }

    @Override
    protected void onPause() {
        mCodeScanner.releaseResources();
        super.onPause();
    }

    private void searchByBarcode(final String barcode) {
        executor.submit(() -> {

            OpenFoodFactsProductDTO dto = barProductOpenFoodFactsService.searchByBarCode(barcode);
            System.out.println(dto.toString());

            runOnUiThread(() -> {
                var newProduct = openFoodDtoToProductModelMapper.map(dto);
                newProduct.setBrand(dto.getBrands().isEmpty() ? "" : dto.getBrands().get(0));
                mockProductRepository.insert(newProduct);
                onBackPressed();
            });
        });
    }
}