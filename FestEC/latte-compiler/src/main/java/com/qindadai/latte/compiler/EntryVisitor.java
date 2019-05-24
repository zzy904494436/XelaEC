package com.qindadai.latte.compiler;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.SimpleAnnotationValueVisitor8;

/**
 * Created by mymac on 2019-05-24.
 * func:
 * 类的方法 和值  的 访问器
 */
public class EntryVisitor extends SimpleAnnotationValueVisitor8<Void, Void> {

    private Filer mFiler;        // 需要遍历的东西
    private TypeMirror mirror;  // 要循环找出的类型
    private String packageName; // 最终拿到的name

    public void setFiler(Filer filer) {
        this.mFiler = filer;
    }

    @Override
    public Void visitString(String s, Void p) {
        packageName = s;
        return p;
    }


    @Override
    public Void visitType(TypeMirror t, Void p) {
        mirror = t;
        return p;
    }

    private void generateJavaCode() {
        final TypeSpec targetActivity = TypeSpec.classBuilder("WXEntryActivity")
                .addModifiers(Modifier.PUBLIC)
                .addModifiers(Modifier.FINAL)
                .superclass(TypeName.get(mirror))
                .build();

        final JavaFile javaFile = JavaFile.builder(packageName + ".wxapi", targetActivity)
                .addFileComment("微信入口文件")
                .build();
        try {
            javaFile.writeTo(mFiler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
