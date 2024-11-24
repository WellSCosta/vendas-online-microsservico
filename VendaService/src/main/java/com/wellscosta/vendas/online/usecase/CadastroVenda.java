package com.wellscosta.vendas.online.usecase;

import com.wellscosta.vendas.online.domain.Produto;
import com.wellscosta.vendas.online.domain.Venda;
import com.wellscosta.vendas.online.dto.VendaDTO;
import com.wellscosta.vendas.online.exception.EntityNotFoundException;
import com.wellscosta.vendas.online.repository.IVendaRepository;
import com.wellscosta.vendas.online.service.ClienteService;
import com.wellscosta.vendas.online.service.IProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;

@Service
public class CadastroVenda {

    private IVendaRepository vendaRepository;

    private IProdutoService produtoService;

    private ClienteService clienteService;

    @Autowired
    public CadastroVenda(IVendaRepository produtoRepository,
                         IProdutoService produtoService,
                         ClienteService clienteService) {
        this.vendaRepository = produtoRepository;
        this.produtoService = produtoService;
        this.clienteService = clienteService;
    }

    public Venda cadastrar(@Valid VendaDTO vendaDTO) {
        Venda venda = convertToDomain(vendaDTO, Venda.Status.INICIADA);
        validarCliente(venda.getClienteId());
        venda.recalcularValorTotalVenda();
        return this.vendaRepository.insert(venda);
    }

    private void validarCliente(String clienteId) {
        Boolean isCadastrado =
                this.clienteService.isClienteCadastrado(clienteId);
        if (!isCadastrado) {
            throw new EntityNotFoundException(Venda.class, "clienteId", clienteId);
        }
    }

    private Venda convertToDomain(@Valid VendaDTO vendaDTO, Venda.Status status) {
        return Venda.builder()
                .clienteId(vendaDTO.getClienteId())
                .codigo(vendaDTO.getCodigo())
                .dataVenda(vendaDTO.getDataVenda())
                .status(status)
                .valorTotal(BigDecimal.ZERO)
                .produtos(new HashSet<>())
                .build();
    }

    public Venda atualizar(@Valid Venda venda) {
        return this.vendaRepository.save(venda);
    }

    public Venda finalizar(String id) {
        Venda venda = buscarVenda(id);
        venda.validarStatus();
        venda.setStatus(Venda.Status.CONCLUIDA);
        return this.vendaRepository.save(venda);
    }

    public Venda cancelar(String id) {
        Venda venda = buscarVenda(id);
        venda.validarStatus();
        venda.setStatus(Venda.Status.CANCELADA);
        return this.vendaRepository.save(venda);
    }

    public Venda adicionarProduto(String id, String codigoProduto, Integer quantidade) {
        Venda venda = buscarVenda(id);
        Produto produto = buscarProduto(codigoProduto);
        venda.validarStatus();
        venda.adicionarProduto(produto, quantidade);
        return this.vendaRepository.save(venda);
    }

    public Venda removerProduto(String id, String codigoProduto, Integer quantidade) {
        Venda venda = buscarVenda(id);
        Produto produto = buscarProduto(codigoProduto);
        venda.validarStatus();
        venda.removerProduto(produto, quantidade);
        return this.vendaRepository.save(venda);
    }

    private Venda buscarVenda(String id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Venda.class, "id", id));

    }

    private Produto buscarProduto(String codigoProduto) {
        Produto prod = produtoService.buscarProduto(codigoProduto);
        if (prod == null) {
            throw new EntityNotFoundException(Produto.class, "codigo", codigoProduto);
        }
        return prod;
    }

}
