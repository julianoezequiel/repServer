package com.api.rep.entity;

import com.api.rep.entity.Rep;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T17:30:03")
@StaticMetamodel(Nsr.class)
public class Nsr_ { 

    public static volatile SingularAttribute<Nsr, String> cpfResponsavel;
    public static volatile SingularAttribute<Nsr, Integer> tipo;
    public static volatile SingularAttribute<Nsr, Date> horaAntesAjuste;
    public static volatile SingularAttribute<Nsr, String> cei;
    public static volatile SingularAttribute<Nsr, String> nomeEmpregado;
    public static volatile SingularAttribute<Nsr, String> local;
    public static volatile SingularAttribute<Nsr, Date> dataAjustada;
    public static volatile SingularAttribute<Nsr, String> crc;
    public static volatile SingularAttribute<Nsr, Date> dataGravacao;
    public static volatile SingularAttribute<Nsr, Integer> id;
    public static volatile SingularAttribute<Nsr, String> pis;
    public static volatile SingularAttribute<Nsr, String> cnpj_cpf;
    public static volatile SingularAttribute<Nsr, String> dadosEmpregado;
    public static volatile SingularAttribute<Nsr, Integer> tipoIndentificador;
    public static volatile SingularAttribute<Nsr, Date> dataAntesAjuste;
    public static volatile SingularAttribute<Nsr, String> tipoOperacao;
    public static volatile SingularAttribute<Nsr, Rep> repId;
    public static volatile SingularAttribute<Nsr, Date> horarioMarcacao;
    public static volatile SingularAttribute<Nsr, Date> horaAjustada;
    public static volatile SingularAttribute<Nsr, String> registro;
    public static volatile SingularAttribute<Nsr, String> tipoEvento;
    public static volatile SingularAttribute<Nsr, Date> horarioGravacao;
    public static volatile SingularAttribute<Nsr, Integer> numeroNsr;
    public static volatile SingularAttribute<Nsr, String> razaoSocial;
    public static volatile SingularAttribute<Nsr, Date> dataMarcacao;

}