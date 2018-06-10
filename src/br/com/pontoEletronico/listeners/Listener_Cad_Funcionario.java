/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pontoEletronico.listeners;

import br.com.pontoEletronico.dal.EntityManagerHelper;
import br.com.pontoEletronico.entities.Funcionario;
import br.com.pontoEletronico.frames.Form_Cad_Funcionario;
import br.com.pontoEletronico.intefaces.ListenerAbstractDefaultAdapter;
import br.com.pontoEletronico.util.MessageFactory;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;

/**
 *
 * @author Tiago
 */
public class Listener_Cad_Funcionario extends ListenerAbstractDefaultAdapter<Form_Cad_Funcionario> {

    private static final long serialVersionUID = 2385717988342261962L;
    private final EntityManagerHelper<Funcionario> emh = new EntityManagerHelper();
    private final Funcionario funcionario = new Funcionario();

    public Listener_Cad_Funcionario(Form_Cad_Funcionario form) {
        super(form);
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent acEvent) {
        super.actionPerformed(acEvent);
        switch (acEvent.getActionCommand()) {
            case "salvar":
                salvar();
                break;
        }
    }

    private void salvar() {
        boolean validar = form.validarCampos();
        if (validar) {
            if (MessageFactory.getSystemMsg(MessageFactory.SALVAR, form)) {
                setDados();
                MessageFactory.getOperationMsg(MessageFactory.SALVAR, form,
                        emh.getOperation(EntityManagerHelper.SALVAR, funcionario, EntityManagerHelper.MYSQL_PU)
                );
                getDados();
            }
        }
    }

    private void setDados() {
        funcionario.setCpf(form.getTxtCpf().getText());
        funcionario.setMatricula(!form.getTxtMatricula().getText().equals("") ? Long.parseLong(form.getTxtMatricula().getText()) : null);
        funcionario.setNome(form.getTxtNome().getText());
        funcionario.setSalario(!form.getTxtSalario().getText().equals("") ? new BigDecimal(form.getTxtSalario().getText()) : null);
        funcionario.setSenha(form.getTxtSenha().getText());
    }

    private void getDados() {
        form.getTxtCpf().setText(funcionario.getCpf());
        form.getTxtMatricula().setText(funcionario.getMatricula() != null ? funcionario.getMatricula().toString() : null);
        form.getTxtNome().setText(funcionario.getNome());
        form.getTxtSalario().setText(funcionario.getSalario() != null ? funcionario.getSalario().toString() : null);
        form.getTxtSenha().setText(funcionario.getSenha());
    }

}
