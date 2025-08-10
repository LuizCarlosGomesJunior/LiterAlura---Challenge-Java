package br.com.alura.literalura.dto;

import java.util.List;

public class ResultadoBusca {

    private Integer count;
    private String next;
    private String previous;
    private List<LivroDto> results;

    public ResultadoBusca() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<LivroDto> getResults() {
        return results;
    }

    public void setResults(List<LivroDto> results) {
        this.results = results;
    }
}
