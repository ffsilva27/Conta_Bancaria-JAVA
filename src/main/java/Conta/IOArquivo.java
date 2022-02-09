package Conta;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static java.nio.file.StandardOpenOption.CREATE;

public class IOArquivo {
    List<ContaBancaria> conta = new LinkedList<>();
    ContaBancaria contaBancaria;
    Map<String, ArrayList<OperacaoBancaria>> map = new HashMap<>();

    public void lerArquivo(){
        String file = "BD.csv";
        try {
            Scanner scan = new Scanner(new File(file));
            scan.nextLine();
            while(scan.hasNextLine()){
                String[] linhaQuebrada = scan.nextLine().split(",");
                contaBancaria = new ContaBancaria(linhaQuebrada[1],linhaQuebrada[2],linhaQuebrada[3],linhaQuebrada[4]);
                contaBancaria.setOperacao(new OperacaoBancaria(linhaQuebrada[5],linhaQuebrada[6],Float.parseFloat(linhaQuebrada[7]),linhaQuebrada[0]));
                contaBancaria.operacoes(linhaQuebrada[5],Float.parseFloat(linhaQuebrada[7]),linhaQuebrada[6],linhaQuebrada[0]);
                if(!map.containsKey(linhaQuebrada[4])){
                    map.put(linhaQuebrada[4], contaBancaria.getOperacaoBancaria());
                }else {
                   if(!map.get(linhaQuebrada[4]).containsAll(contaBancaria.getOperacaoBancaria())){
                       map.get(linhaQuebrada[4]).add(contaBancaria.getOperacao());
                   }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Erro durante leitura.");
            e.printStackTrace();
        }
    }

    public void imprimir(){
        Set<String> keySet = map.keySet();
        ArrayList<String> listaDeChaves = new ArrayList<>(keySet);
        for(String key : listaDeChaves) {
            StringBuilder sbExtrato = new StringBuilder("{");
            sbExtrato.append("\nN°Conta: " + key + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" + "Saldo da conta R$" + imprimirSaldo(key) + "\n");
            for(Map.Entry<String, ArrayList<OperacaoBancaria>> entrada : map.entrySet()){
                if(entrada.getKey()==key){
                    Collections.sort(entrada.getValue(), Comparator.comparing(OperacaoBancaria::getDataHoraOperacao));
                    for(OperacaoBancaria op : entrada.getValue()){
                        sbExtrato.append("[").append(op).append("\n");
                    }
                }
                criarArquivo(key, sbExtrato);
            }
            System.out.println(sbExtrato);
        }
    }

    public float imprimirSaldo(String key) {
        float totalConta = 0;
        float valorOperacaoAux=0;
        for(OperacaoBancaria op :  map.get(key)){
            if(op.getTipoOperacao().toLowerCase().equals("saque")) {
                valorOperacaoAux = op.getValorOperacao()*-1;
            }else {
                valorOperacaoAux = op.getValorOperacao();
            }
            totalConta += valorOperacaoAux;
        }
        return totalConta;
    }

    public void criarArquivo(String nConta, StringBuilder sb) {
        File novoArquivo = new File("C:\\Users\\Filip\\Desktop\\Projeto - Dep.Externas\\src\\main\\resources\\" + nConta+".txt");
        try {
            novoArquivo.createNewFile();
            gerarExtrato(novoArquivo.getAbsolutePath(), sb);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gerarExtrato(String path, StringBuilder sb) {
        Path caminho = Paths.get(path);
        try{
            Files.writeString(caminho, sb, CREATE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
