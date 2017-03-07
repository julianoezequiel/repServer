package com.api.rep.entity;

import com.api.rep.entity.Rep;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-03-07T16:33:27")
@StaticMetamodel(Empregador.class)
public class Empregador_ { 

    public static volatile SingularAttribute<Empregador, String> empregadorRazao;
    public static volatile SingularAttribute<Empregador, String> empregadorTipoIdent;
    public static volatile SingularAttribute<Empregador, String> empregadorIdent;
    public static volatile SingularAttribute<Empregador, String> empregadorCei;
    public static volatile SingularAttribute<Empregador, Integer> id;
    public static volatile SingularAttribute<Empregador, String> empregadorLocal;
    public static volatile CollectionAttribute<Empregador, Rep> repCollection;

}