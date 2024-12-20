PGDMP     )    6            	    |            serieATeams    15.2    15.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    51719    serieATeams    DATABASE     �   CREATE DATABASE "serieATeams" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Croatian_Croatia.1250';
    DROP DATABASE "serieATeams";
                postgres    false            �            1259    51725    players    TABLE     �   CREATE TABLE public.players (
    playerid integer NOT NULL,
    playerfirstname character varying(50) NOT NULL,
    playerlastname character varying(50) NOT NULL,
    "position" character varying(20) NOT NULL,
    teamid integer
);
    DROP TABLE public.players;
       public         heap    postgres    false            �            1259    51720    teams    TABLE     H  CREATE TABLE public.teams (
    teamid integer NOT NULL,
    team character varying(50),
    goals integer,
    shotspg double precision,
    yellow_cards integer,
    red_cards integer,
    avgpossessionperc double precision,
    avgpassaccperc double precision,
    aerialswon double precision,
    rating double precision
);
    DROP TABLE public.teams;
       public         heap    postgres    false            �          0    51725    players 
   TABLE DATA           `   COPY public.players (playerid, playerfirstname, playerlastname, "position", teamid) FROM stdin;
    public          postgres    false    215   �       �          0    51720    teams 
   TABLE DATA           �   COPY public.teams (teamid, team, goals, shotspg, yellow_cards, red_cards, avgpossessionperc, avgpassaccperc, aerialswon, rating) FROM stdin;
    public          postgres    false    214   �       k           2606    51729    players players_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_pkey PRIMARY KEY (playerid);
 >   ALTER TABLE ONLY public.players DROP CONSTRAINT players_pkey;
       public            postgres    false    215            i           2606    51724    teams teams_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.teams
    ADD CONSTRAINT teams_pkey PRIMARY KEY (teamid);
 :   ALTER TABLE ONLY public.teams DROP CONSTRAINT teams_pkey;
       public            postgres    false    214            l           2606    51730    players players_teamid_fkey    FK CONSTRAINT     }   ALTER TABLE ONLY public.players
    ADD CONSTRAINT players_teamid_fkey FOREIGN KEY (teamid) REFERENCES public.teams(teamid);
 E   ALTER TABLE ONLY public.players DROP CONSTRAINT players_teamid_fkey;
       public          postgres    false    215    3177    214            �   L  x�eV�r�H<W�|�z �x�?f�A�L�,���&Z���7c$�A���,"��lM�ךf�j�,����^<E*��Z;�o����BVb��Д=~��e%�T�0�B�������g��V��˾ :��V,�^�q�������bA��Ł��t�Fx�M[�s�ت�)VczltEO�G��.V�N�7�Z�MYr�6Qр^7��x�x��>0���.�F��*#��g=��m� ��.�W�׶ǘ�(�i�=��咻�6�E��*�zto���-�2Z�v��r��}2��"T虋0�.�PEcȊOm^z�m�Nh���;�_��"�x��b�{ǻ��(:��ms��uY3�a�\m�)�c����T/����ڇ��#*���[�K��s;g@�sQ�^�u�摊3�/�{X����37�޳M�˹>5�ό��y�r�E�%����xB���:�{���*��ܡ���HD�ki���z��cz]3� "��HB3sGS�{l��A	G���Ҳ���"�>�����Q��i� �h��'���u�s��J���}4_����B ���;��ݵgT:������,���m�N�*�����ވ#�N��8�[,��yP�,deh)�U�凈��.
-��\���KqV ����*�`�v(�Н�
����t�؜�M-D��r�H������k$���]U�&%j����#����b��Ν�
�WBU��\倰��_�2 ��}�̟����:J�0ŎP�_��u�a�`���/+��~E�DS�w�j�[f�A�5n	l�������� �?��c)�Э����`�k��Q��(vŖ;�HD�Zj�۫x��Z7�Le�Ŷ�b��[<��lWK6�T���n=�|���o��c9x�'u�w���c ���s��0\3�1�R0Ɨ��q��M�w�R.@ԣ�� K�T��,ǣ�<��
L�>_}�FHL����+����������l�{�Fi�=o\�:�@ѩ��1f�bѯ���h��_�|�0����Z�{�j�X`1`Nҙn@&a-�6���l�n�$���/���5
j      �   �  x�E�͊�@��5O�'Ꙟ��!!!	!�\��X���v����yTS�uuK���wZn�+r��!#'$�8(JD�)%:��[;���PGH��)! �!����
�����ϳ����[F("݊����;ŗv�%A���QAɬ-T����ܦ��,"VxD�mE]·~nH���*����7�o^Y\��v����#I*��447�,)�b��奯sc9�e�D�dv��o:W���*t��C����됨�NF�0ϥ#�$����XJ�%Ycu"�5�}i�@��j��jO����I�Ǳ���Λ�I�@�h�s%@�R��I���j�q�e3�`��MhcHN?_�e�NP���
C�V<�a�F'痢nFE��mIU�5Y�L�$���j�7/���l"Յ	[V�ee�Lo��v\=ck�pAL���_�zn�c;���A��*/�#����{��Znǵ��b�E���Gw�i�!��G<O���Cmr�/�Ԅ�yoQw���9�z�l     