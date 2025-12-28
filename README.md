## Aplicativo Convidados

**Principais Recursos e Componentes:**

* **Banco de Dados:** Implementa um banco de dados Room (`GuestDataBase`, `GuestDAO`) para armazenar informações de hóspedes (entidade `GuestData`), incluindo nome e status de presença. Um `GuestRepository` é adicionado para abstrair as operações de dados.

* **UI/UX:**

* Configura a `MainActivity` com um `DrawerLayout` e uma `NavigationView` para navegar entre diferentes listas de hóspedes.

* Inclui um `FloatingActionButton` para iniciar a `GuestFormActivity`.

* A `GuestFormActivity` permite adicionar e editar detalhes do hóspede (nome e presença).

* **Listas de Convidados:**

* Cria fragmentos para exibir todos os convidados (`AllGuestsFragment`), os convidados presentes (`AllPresentsFragment`) e os convidados ausentes (`AllAbsentsFragment`).

* Utiliza um `RecyclerView` com um `GuestsAdapter` e um `GuestViewHolder` para exibir as listas.

* Implementa listeners de clique (`OnGuestListenner`) para editar (clique curto) e excluir (clique longo com caixa de diálogo de confirmação) convidados.

* **Arquitetura MVVM:**

* Introduz o `GuestsViewModel` para buscar e gerenciar as listas de convidados para os fragmentos.

* Adiciona o `GuestFormViewModel` para lidar com o salvamento, atualização e carregamento de dados individuais de convidados.

* **Navegação:** Configura o componente de navegação (`mobile_navigation.xml`) para lidar com as transações de fragmentos na atividade principal.



* <img width="270" height="570" alt="Screenshot_20251228_153919" src="https://github.com/user-attachments/assets/c9ee3732-183e-4fcd-959d-afe34ec17690" />


<img width="270" height="570" alt="Screenshot_20251228_153948" src="https://github.com/user-attachments/assets/002e7178-7ad3-4a68-8b50-7975edda01f0" />


<img width="270" height="570" alt="Screenshot_20251228_154140" src="https://github.com/user-attachments/assets/d11e478b-e6e8-40f2-a58c-33297e2046b3" />




