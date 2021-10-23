package collections.interfaces;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

/**
 * Contrato para a implementação de uma lista ligada.
 *
 * @param <T> Tipo a ser armazenado pela lista.
 */
public interface IList<T> {
    /**
     * Adiciona um elemento à lista.
     *
     * @param elem Elemento a ser adicionada.
     * @throws NullPointerException Caso o elemento recebido seja null.
     */
    public void add(T elem) throws NullPointerException;

    /**
     * Método para remover um elemnto da lista.
     *
     * @param elem Elemento a ser removido da lista.
     * @return True caso o objeto tenha sido removido, False caso não tenha sido encontrado.
     * @throws NoSuchElementException É lançado uma exceção caso a lista esteja vazia.
     */
    public boolean remove(T elem) throws NoSuchElementException;

    /**
     * Retorna um elemento da lista.
     * @param index Posição a retornar.
     * @return Elemento pretendido.
     * @throws IndexOutOfBoundsException Caso o index não seja válido.
     * @throws EmptyStackException Caso a lista esteja vazia
     */
    public T get(int index) throws IndexOutOfBoundsException, EmptyStackException;

    /**
     * Retorna o tamanho da lista.
     *
     * @return Tamanho da lista.
     */
    public int size();

    /**
     * Método que diz se a lista está vazia.
     *
     * @return True em caso afirmativo, False caso contrário.
     */
    public boolean isEmpty();

    /**
     * Método que limpa toda a lista.
     */
    public void clear();
}
