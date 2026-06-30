package com.example.primeiroAppSpring.controller;

import com.example.primeiroAppSpring.model.UsuarioForm;
import com.example.primeiroAppSpring.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/cadastro")
    public String exibirCadastro(Model model){
        model.addAttribute("usuarioForm",new UsuarioForm());

        model.addAttribute("tituloPagina","Cadastro");

        model.addAttribute("subTituloPagina","Sistema de Gerenciamento de Estoque da Cozinha");
        return  "cadastro";
    }

    @PostMapping("/cadastro")
    public String processarCadastro(@ModelAttribute UsuarioForm form, Model model){
        String erro = usuarioService.cadastrar(form);

        if(erro!=null){
            model.addAttribute("erro", erro);
            model.addAttribute("usuarioform",form);

            return "cadastro";
        }
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String exibirLogin(Model model){
        model.addAttribute("usuarioForm",new UsuarioForm());

        model.addAttribute("tituloPagina","Login");

        model.addAttribute("subTituloPagina","Sistema de Gerenciamento de Estoque da Cozinha");
        return  "login";
    }

    @PostMapping("/login")
    public String processarLogin(@ModelAttribute UsuarioForm form, Model model){
        if (form.getEmail().endsWith ("@df.senac.br")){
            return "redirect:/";
        }
        model.addAttribute("erro", "E-mail ou senha inválidos");
        IO.println(form.getNome()+" "+form.getEmail());

        return "login";
    }


    @GetMapping("/alterar-senha")
    public String exibirAlterarSenha(Model model){
        model.addAttribute("usuarioForm",new UsuarioForm());

        model.addAttribute("tituloPagina","Alterar Senha");

        model.addAttribute("subTituloPagina","Informe a sua Senha");
        return  "alterarSenha";
    }



    @PostMapping("/alterar-senha")
    public String processarAlterarSenha(@ModelAttribute UsuarioForm form, Model model){
        if (!form.getSenha().equals(form.getConfirmarSenha())){
            model.addAttribute("erro","ERRO, as senhas não conferem");
            return "alterar-senha";
        }

        model.addAttribute("SUCESSO!", " Senha alterada com SUCESSO!");

        return "redirect:/login";
    }

}


