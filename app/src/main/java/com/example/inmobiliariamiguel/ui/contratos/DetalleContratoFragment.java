package com.example.inmobiliariamiguel.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.inmobiliariamiguel.R;
import com.example.inmobiliariamiguel.modelo.Contrato;

public class DetalleContratoFragment extends Fragment {

    private DetalleContratoViewModel mViewModel;
    private TextView tvCodigoContrato;
    private TextView tvFechaInicio;
    private TextView tvFechaFinalizacion;
    private TextView tvMontoDeAlquier;
    private TextView tvInquilinoContrato;
    private TextView tvInmuebleContrato;
    private Button btPagos;

    public static DetalleContratoFragment newInstance() {
        return new DetalleContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_detalle_contrato , container, false);

        inicializar(root);
        return root;
    }

    private void inicializar(View view) {
        tvCodigoContrato = view.findViewById(R.id.tvCodigoContrato);
        tvFechaInicio = view.findViewById(R.id.tvFechaInicio);
        tvFechaFinalizacion = view.findViewById(R.id.tvFechaFinalizacion);
        tvMontoDeAlquier = view.findViewById(R.id.tvMontoDeAlquier);
        tvInquilinoContrato = view.findViewById(R.id.tvInquilinoContrato);
        tvInmuebleContrato = view.findViewById(R.id.tvInmuebleContrato);
        btPagos              = view.findViewById(R.id.btPagos);


        mViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DetalleContratoViewModel.class);

        mViewModel.getContrato().observe(getActivity(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato contrato) {
                tvCodigoContrato.setText(contrato.getIdContrato() + "");
                tvFechaInicio.setText(contrato.getFechaInicio());
                tvFechaFinalizacion.setText(contrato.getFechaFin());
                tvMontoDeAlquier.setText("$ " +contrato.getMontoAlquiler());
                tvInquilinoContrato.setText(contrato.getInquilino().toString());
                tvInmuebleContrato.setText(contrato.getInmueble().getDireccion());
            }
        });

        btPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contrato con = new Contrato();
                con.setIdContrato(Integer.parseInt(tvCodigoContrato.getText().toString()));
                Bundle bundle = new Bundle();
                bundle.putSerializable("contrato", con);
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main).navigate(R.id.pagoContratoFragment, bundle);
            }
        });


        mViewModel.setContrato(getArguments());
    }
}